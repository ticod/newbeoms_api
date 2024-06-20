package com.ticod.newbeoms_api.service;

import com.ticod.newbeoms_api.dto.*;
import com.ticod.newbeoms_api.entity.*;
import com.ticod.newbeoms_api.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PublicationService {

    private final PublicationRepository publicationRepository;
    private final NewsRepository newsRepository;
    private final ComingSoonRepository comingSoonRepository;
    private final WorkCitedRepository workCitedRepository;
    private final ComingSoonContentRepository comingSoonContentRepository;
    private final GossipRepository gossipRepository;
    private final GossipLinkRepository gossipLinkRepository;
    private final HardwareNewsRepository hardwareNewsRepository;

    public PublicationService(PublicationRepository publicationRepository, NewsRepository newsRepository, ComingSoonRepository comingSoonRepository, WorkCitedRepository workCitedRepository,
                              ComingSoonContentRepository comingSoonContentRepository,
                              GossipRepository gossipRepository,
                              GossipLinkRepository gossipLinkRepository, HardwareNewsRepository hardwareNewsRepository) {
        this.publicationRepository = publicationRepository;
        this.newsRepository = newsRepository;
        this.comingSoonRepository = comingSoonRepository;
        this.workCitedRepository = workCitedRepository;
        this.comingSoonContentRepository = comingSoonContentRepository;
        this.gossipRepository = gossipRepository;
        this.gossipLinkRepository = gossipLinkRepository;
        this.hardwareNewsRepository = hardwareNewsRepository;
    }

    /**
     * 출판 날짜 찾기
     */
    public Publication getPublication(LocalDate date) {
        return publicationRepository.findPublicationByPublicationDate(date);
    }

    /**
     * 출판물 전체 내용 추가
     */
    @Transactional
    public void addPublcation(PublicationDto publicationDto) {
        Publication publication = Publication.builder()
                .publicationDate(publicationDto.getPublicationDate()).build();

        if (getPublication(publicationDto.getPublicationDate()) != null) {
            return;
        }
        publicationRepository.save(publication);
        addNewsList(publicationDto.getNewsDtoList(), publication);
        addComingSoonList(publicationDto.getComingSoonDtoList(), publication);
        addGossipList(publicationDto, publication);
        workCitedRepository.saveAll(
                publicationDto.getWorkCitedDtoList().stream()
                        .map(workCitedDto -> workCitedDto.toEntity(publication)).toList()
        );
        hardwareNewsRepository.saveAll(
                publicationDto.getHardwareNewsDtoList().stream()
                        .map(hardwareNewsDto -> hardwareNewsDto.toEntity(publication)).toList()
        );

    }

    @Transactional
    protected void addGossipList(PublicationDto publicationDto, Publication publication) {
        List<Gossip> gossipList = new ArrayList<>();
        List<GossipLink> gossipLinkList = new ArrayList<>();
        for (GossipDto gossipDto : publicationDto.getGossipDtoList()) {
            Gossip target = Gossip.builder()
                    .publication(publication)
                    .title(gossipDto.getTitle())
                    .build();
            gossipList.add(target);
            for (String gossipLink : gossipDto.getLinks()) {
                gossipLinkList.add(GossipLink.builder()
                        .gossip(target).link(gossipLink).build());
            }
        }
        gossipRepository.saveAll(gossipList);
        gossipLinkRepository.saveAll(gossipLinkList);
    }

    private void addComingSoonList(List<ComingSoonDto> comingSoonDtoList, Publication publication) {
        List<ComingSoon> comingSoonList = new ArrayList<>();
        List<ComingSoonContent> comingSoonContentList = new ArrayList<>();
        for (ComingSoonDto comingSoonDto : comingSoonDtoList) {
            ComingSoon target = ComingSoon.builder()
                    .publication(publication)
                    .comingSoonDate(comingSoonDto.getDate()).build();
            comingSoonList.add(target);
            for (String comingSoonContent : comingSoonDto.getContents()) {
                comingSoonContentList.add(ComingSoonContent.builder()
                        .comingSoon(target)
                        .content(comingSoonContent).build());
            }
        }
        comingSoonRepository.saveAll(comingSoonList);
        comingSoonContentRepository.saveAll(comingSoonContentList);
    }

    @Transactional
    protected void addNewsList(List<NewsDto> newsDtoList, Publication publication) {
        List<News> target = newsDtoList.stream().map(
                newsDto -> News.builder().title(newsDto.getTitle())
                        .publication(publication)
                        .subtitle(newsDto.getSubtitle())
                        .newsDate(newsDto.getDate())
                        .content(newsDto.getContent())
                        .imagePath(newsDto.getImagePath())
                        .build()
        ).toList();
        newsRepository.saveAll(target);
    }

    /**
     * 출간 날짜로 PublicationDto 생성
     */
    public PublicationDto getPublicationDto(LocalDate date) {
        Publication publication = publicationRepository.findPublicationByPublicationDate(date);
        if (publication == null) {
            throw new IllegalStateException();
        }

        List<NewsDto> newsDtoList = newsRepository.findByPublication(publication)
                .stream().map(NewsDto::from).toList();
        List<GossipDto> gossipDtoList = gossipRepository.findByPublication(publication)
                .stream().map(GossipDto::from).toList();
        List<ComingSoonDto> comingSoonDtoList = comingSoonRepository.findByPublication(publication)
                .stream().map(ComingSoonDto::from).toList();
        List<HardwareNewsDto> hardwareNewsDtoList = hardwareNewsRepository.findByPublication(publication)
                .stream().map(HardwareNewsDto::from).toList();
        List<WorkCitedDto> workCitedDtoList = workCitedRepository.findByPublication(publication)
                .stream().map(WorkCitedDto::from).toList();

        return PublicationDto.builder()
                .publicationDate(publication.getPublicationDate())
                .newsDtoList(newsDtoList)
                .gossipDtoList(gossipDtoList)
                .comingSoonDtoList(comingSoonDtoList)
                .hardwareNewsDtoList(hardwareNewsDtoList)
                .workCitedDtoList(workCitedDtoList)
                .build();
    }

    public List<PublicationDateDto> getPublicationDates() {
        return new ArrayList<>();
    }
}
