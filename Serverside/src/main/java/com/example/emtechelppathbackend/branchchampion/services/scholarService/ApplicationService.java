package com.example.emtechelppathbackend.branchchampion.services.scholarService;

import com.example.emtechelppathbackend.branchchampion.entities.Application;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ApplicationService {
    Application addNewApplication(Application application);

    Optional<Application> updateApplicationById(Long id, Application application);

    void deleteApplicationById(Long id);

    List<Application> displayApplications();

    Application displayApplicationDetailsById(Long id);

    long getTotalApplications();

    long getAwardedApplications();
}
