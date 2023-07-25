package com.example.emtechelppathbackend.services;

import com.example.emtechelppathbackend.dtos.FeedsDto;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface FeedsService  {

    List<FeedsDto> getAllUsersFeeds();
    FeedsDto addFeeds(Long user_id, FeedsDto feedsDto);
    List<FeedsDto> getFeedById(Long Id);
    FeedsDto updateFeedsById(Long id, FeedsDto feedsDto);
    List<FeedsDto> getFeedByUserId(Long user_id);

}


