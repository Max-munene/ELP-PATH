package com.example.emtechelppathbackend.services.ServiceImpl;


import com.example.emtechelppathbackend.dtos.FeedsDto;
import com.example.emtechelppathbackend.entities.Feeds;
import com.example.emtechelppathbackend.repositories.FeedsRepository;
import com.example.emtechelppathbackend.security.entities.Users;
import com.example.emtechelppathbackend.security.repositories.UserRepository;
import com.example.emtechelppathbackend.services.FeedsService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
class FeedsServiceImpl implements FeedsService {

    private final FeedsRepository feedsRepository;
    private  final UserRepository usersRepository;
    private FeedsDto feedsDto;

    public FeedsServiceImpl(FeedsRepository feedsRepository, UserRepository usersRepository) {
        this.feedsRepository = feedsRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public List<FeedsDto> getAllUsersFeeds(){
        return feedsRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


    @Override
    public FeedsDto addFeeds(Long user_id, FeedsDto feedsDto) {
        Feeds feeds = mapToEntity(feedsDto);
        Users users = usersRepository.findById(user_id).orElseThrow(()->new RuntimeException("user not found"));
          feeds.setUser(users);
          feeds.setPostDate(feedsDto.getPostDate());
          feeds.setDescription(feedsDto.getDescription());
          Feeds newFeeds = feedsRepository.save(feeds);

        return mapToDto(newFeeds);
    }

    @Override
    public List<FeedsDto> getFeedById(Long id) {
        return feedsRepository.findById(id)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors
                        .toList());
    }

    @Override
    public FeedsDto updateFeedsById(Long id, FeedsDto feedsDto) {
        Feeds feed = feedsRepository.findById(id).orElseThrow();
        feed.setDescription(feedsDto.getDescription());
        feed.setPostDate(feedsDto.getPostDate());
        return mapToDto(feed);
    }


    @Override
    public List<FeedsDto> getFeedByUserId(Long user_id) {
        return feedsRepository.findById(user_id)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors
                        .toList());
    }

    private Feeds mapToEntity(FeedsDto feedsDto) {
        Feeds feeds = new Feeds();
        feeds.setPostDate(feedsDto.getPostDate());
        feeds.setDescription(feedsDto.getDescription());
        return feeds;
    }
    private FeedsDto mapToDto(Feeds feeds) {
        FeedsDto feedsDto = new FeedsDto();
        feedsDto.setId(feeds.getId());
        feedsDto.setDescription(feeds.getDescription());
        return feedsDto;
    }
}