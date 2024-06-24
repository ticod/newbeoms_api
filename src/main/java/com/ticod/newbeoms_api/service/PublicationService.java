package com.ticod.newbeoms_api.service;

import com.ticod.newbeoms_api.entity.*;
import com.ticod.newbeoms_api.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
     * 출판물 전체 추가
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

    public List<News> getNewsList(Publication publication) {
        return newsRepository.findByPublication(publication);
    }

    public List<Gossip> getGossipList(Publication publication) {
        return gossipRepository.findByPublication(publication);
    }

    public List<ComingSoon> getComingSoonList(Publication publication) {
        return comingSoonRepository.findByPublication(publication);
    }

    public List<HardwareNews> getHardwareNewsList(Publication publication) {
        return hardwareNewsRepository.findByPublication(publication);
    }

    public List<WorkCited> getWorkCitedList(Publication publication) {
        return workCitedRepository.findByPublication(publication);
    }

    public List<Tag> getTagsByContents(List<String> searchKeywords) {
        Set<Tag> tagSet = new HashSet<>();
        for (String searchKeyword : searchKeywords) {
            List<Tag> tags = tagRepository.findByContentContaining(searchKeyword);
            tagSet.addAll(tags);
        }
        return tagSet.stream().toList();
    }

    /**
     *  모든 Publication 반환
     */
    public List<Publication> getPublications() {
        return publicationRepository.findAll();
    }
}
