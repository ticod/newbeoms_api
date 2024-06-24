package com.ticod.newbeoms_api.service;

import com.ticod.newbeoms_api.dto.*;
import com.ticod.newbeoms_api.entity.*;
import com.ticod.newbeoms_api.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

//    TODO: Service 단에서 DTO 제거하기
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
    private final TagRepository tagRepository;
    private final NewsTagRepository newsTagRepository;

    public PublicationService(PublicationRepository publicationRepository, NewsRepository newsRepository, ComingSoonRepository comingSoonRepository, WorkCitedRepository workCitedRepository,
                              ComingSoonContentRepository comingSoonContentRepository,
                              GossipRepository gossipRepository,
                              GossipLinkRepository gossipLinkRepository, HardwareNewsRepository hardwareNewsRepository,
                              TagRepository tagRepository, NewsTagRepository newsTagRepository) {
        this.publicationRepository = publicationRepository;
        this.newsRepository = newsRepository;
        this.comingSoonRepository = comingSoonRepository;
        this.workCitedRepository = workCitedRepository;
        this.comingSoonContentRepository = comingSoonContentRepository;
        this.gossipRepository = gossipRepository;
        this.gossipLinkRepository = gossipLinkRepository;
        this.hardwareNewsRepository = hardwareNewsRepository;
        this.tagRepository = tagRepository;
        this.newsTagRepository = newsTagRepository;
    }

    /**
     * 출판 날짜 찾기
     */
    public Publication getPublication(LocalDate date) {
        return publicationRepository.findPublicationByPublicationDate(date);
    }

    /**
     * 출판물 전체 추가 - DTO 제거
     */
    @Transactional
    public void addPublication(Publication publication,
                               Map<News, List<Tag>> newsTagMap,
                               Map<Gossip, List<GossipLink>> gossipLinkMap,
                               Map<ComingSoon, List<ComingSoonContent>> comingSoonContentMap,
                               List<HardwareNews> hardwareNewsList,
                               List<WorkCited> workCitedList) {

        publicationRepository.save(publication);

        saveNewsAndTag(newsTagMap);

        gossipRepository.saveAll(gossipLinkMap.keySet());
        gossipLinkRepository.saveAll(gossipLinkMap.values().stream()
                .flatMap(List::stream).collect(Collectors.toList()));

        comingSoonRepository.saveAll(comingSoonContentMap.keySet());
        comingSoonContentRepository.saveAll(comingSoonContentMap.values().stream()
                .flatMap(List::stream).collect(Collectors.toList()));

        hardwareNewsRepository.saveAll(hardwareNewsList);
        workCitedRepository.saveAll(workCitedList);
    }

    private void saveNewsAndTag(Map<News, List<Tag>> newsTagMap) {
        for (News news : newsTagMap.keySet()) {
            List<NewsTag> newsTagList = new ArrayList<>();
            for (Tag tag : newsTagMap.get(news)) {
                Tag findTag = tagRepository.findByContent(tag.getContent());
                if (findTag == null) {
                    tagRepository.save(tag);
                    newsTagList.add(NewsTag.builder().news(news).tag(tag).build());
                } else {
                    newsTagList.add(NewsTag.builder().news(news).tag(findTag).build());
                }
            }
            newsRepository.save(news);
            newsTagRepository.saveAll(newsTagList);
        }
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
        return publicationRepository.findAll()
                .stream().map(PublicationDateDto::from).toList();
    }

    public List<Tag> getTagsByContents(List<String> searchKeywords) {
        Set<Tag> tagSet = new HashSet<>();
        for (String searchKeyword : searchKeywords) {
            List<Tag> tags = tagRepository.findByContentContaining(searchKeyword);
            tagSet.addAll(tags);
        }
        return tagSet.stream().toList();
    }
}
