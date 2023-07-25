package com.example.emtechelppathbackend.services.ServiceImpl;


import com.example.emtechelppathbackend.dtos.userProfile.EducationDto;
import com.example.emtechelppathbackend.entities.userProfile.Education;
import com.example.emtechelppathbackend.exceptions.UserDetailsNotFoundException;
import com.example.emtechelppathbackend.repositories.EducationRepo;
import com.example.emtechelppathbackend.security.entities.Users;
import com.example.emtechelppathbackend.security.repositories.UserRepository;
import com.example.emtechelppathbackend.services.EducationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class EducationServiceImpl implements EducationService {
    private final EducationRepo educationRepo;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public EducationServiceImpl(EducationRepo educationRepo, UserRepository userRepository, ModelMapper modelMapper) {
        this.educationRepo = educationRepo;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EducationDto addEducation(EducationDto educationDto, Long userId) {
        Education education = mapToEntity(educationDto);

        Users users = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user with that id not found"));
        education.setUser(users);
        education.setId(education.getId());
        education.setSchool_name(education.getSchool_name());
        education.setCourse(education.getCourse());
        education.setStart_date(education.getStart_date());
        education.setEnd_date(education.getEnd_date());

        Education newEducation = educationRepo.save(education);

        return mapToDto(newEducation);
    }

    @Override
    public List<EducationDto> getEducationByUserId(Long userId) {
        List<Education> educations = educationRepo.findByUserId(userId);
        return educations.stream().map(this::mapToDto).collect(Collectors.toList());
    }
//Not working getting constrain validation error
    @Override
    public Education updateEducation(Long userId, Long educationId, Education education) {
        Users users = userRepository.findById(userId).orElseThrow(() -> new UserDetailsNotFoundException("user not found"));
       education = educationRepo.findById(educationId).orElseThrow(() -> new UserDetailsNotFoundException("education for this user not found"));
        if (!Objects.equals(education.getUser().getId(), users.getId())) {
            throw new UserDetailsNotFoundException("this education details do not belong to this user");
        }
            education.setSchool_name(education.getSchool_name());
            education.setCourse(education.getCourse());
            education.setStart_date(education.getStart_date());
            education.setEnd_date(education.getEnd_date());
            Education updatedEducation = educationRepo.save(education);
            return updatedEducation;
        }


    @Override
    public EducationDto updateUserEducation(Long userId, Long educationId, EducationDto educationDto) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new UserDetailsNotFoundException("User not found"));

        Education education = educationRepo.findById(educationId)
                .orElseThrow(() -> new UserDetailsNotFoundException("Education not found"));

        if (!Objects.equals(education.getUser().getId(), user.getId())) {
            throw new UserDetailsNotFoundException("This education does not belong to this user");
        }

        // Update the education fields
        education.setSchool_name(educationDto.getSchool_name());
        education.setCourse(educationDto.getCourse());
        education.setStart_date(educationDto.getStart_date());
        education.setEnd_date(educationDto.getEnd_date());
        education.setGrade(educationDto.getGrade());

        // Save the updated education
        educationRepo.save(education);
        return mapToDto(education);
    }


    @Override
    public void deleteEducation(Long userId, Long educationId) {
        Users users = userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        Education education = educationRepo.findById(educationId).orElseThrow(()->new RuntimeException("education for this user not found"));
        if (!Objects.equals(education.getUser().getId(), users.getId())){
            throw new RuntimeException("this education details do not belong to this user");
        }
        educationRepo.delete(education);
    }

    private EducationDto mapToDto(Education education){
        EducationDto educationDto = new EducationDto();
        educationDto.setId(education.getId());
        educationDto.setSchool_name(education.getSchool_name());
        educationDto.setStart_date(education.getStart_date());
        educationDto.setEnd_date(education.getEnd_date());
        educationDto.setCourse(education.getCourse());
        return educationDto;
    }
    private Education mapToEntity(EducationDto educationDto){
        Education education = new Education();
        education.setId(educationDto.getId());
        education.setSchool_name(educationDto.getSchool_name());
        education.setCourse(educationDto.getCourse());
        education.setStart_date(educationDto.getStart_date());
        education.setEnd_date(educationDto.getEnd_date());
        return education;
    }
}
