package com.example.emtechelppathbackend.services.ServiceImpl;

import com.example.emtechelppathbackend.dtos.userProfile.CareerDto;
import com.example.emtechelppathbackend.entities.userProfile.Career;
import com.example.emtechelppathbackend.repositories.CareerRepo;
import com.example.emtechelppathbackend.security.entities.Users;
import com.example.emtechelppathbackend.security.repositories.UserRepository;
import com.example.emtechelppathbackend.services.CareerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CareerServiceImpl implements CareerService {
    private final CareerRepo careerRepo;
    private final UserRepository userRepository;

    public CareerServiceImpl(CareerRepo careerRepo,
                             UserRepository userRepository) {
        this.careerRepo = careerRepo;

        this.userRepository = userRepository;
    }

    @Override
    public CareerDto addSkills(CareerDto careerDto, Long userId) {
        Career career = mapToEntity(careerDto);
        Users users = userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        career.setUser(users);
        career.setCompanyName(careerDto.getCompanyName());
        career.setDescription(careerDto.getDescription());
        career.setStart_date(careerDto.getStart_date());
        career.setEnd_date(careerDto.getEnd_date());
        Career newCareer = careerRepo.save(career);
        return mapToDto(newCareer);
    }

    @Override
    public List<CareerDto> viewCareerByUserId(Long userId) {
        List<Career> careers = careerRepo.findByUserId(userId);
        return careers.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CareerDto updateCareer(Long userId, Long careerId, CareerDto careerDto) {
        Users user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        Career career = careerRepo.findById(careerId).orElseThrow(()->new RuntimeException("career cannot be found"));
        if (!Objects.equals(career.getUser().getId(), user.getId())){
            throw new RuntimeException("this career do not belong to this user");
        }
        career.setTitle(careerDto.getTitle());
        career.setCompanyName(careerDto.getCompanyName());
        career.setDescription(careerDto.getDescription());
        career.setStart_date(careerDto.getStart_date());
        career.setEnd_date(careerDto.getEnd_date());
        Career updatedCareer = careerRepo.save(career);
        return mapToDto(updatedCareer);
    }

    @Override
    public void deleteCareer(Long userId, Long careerId) {
        Users user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        Career career = careerRepo.findById(careerId).orElseThrow(()->new RuntimeException("career cannot be found"));
        if (!Objects.equals(career.getUser().getId(), user.getId())){
            throw new RuntimeException("this career do not belong to this user");
        }
        careerRepo.delete(career);

    }

    private CareerDto mapToDto(Career career){
        CareerDto careerDto = new CareerDto();
        careerDto.setId(career.getId());
        careerDto.setTitle(career.getTitle());
        careerDto.setDescription(career.getDescription());
        careerDto.setStart_date(career.getStart_date());
        careerDto.setEnd_date(career.getEnd_date());
        careerDto.setCompanyName(career.getCompanyName());
        return careerDto;
    }
    private Career mapToEntity(CareerDto careerDto){
        Career career = new Career();
        career.setId(careerDto.getId());
        career.setTitle(careerDto.getTitle());
        career.setDescription(careerDto.getDescription());
        career.setStart_date(careerDto.getStart_date());
        career.setEnd_date(careerDto.getEnd_date());
        return career;
    }
}
