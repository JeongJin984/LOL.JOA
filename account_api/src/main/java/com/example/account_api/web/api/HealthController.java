package com.example.account_api.web.api;

import com.example.account_api.web.service.LocalHostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class HealthController {
    private final ApplicationAvailability availability;
    private final ApplicationEventPublisher eventPublisher;
    private final LocalHostService localHostService;

    @GetMapping("/hello")
    public String hello() {
        return "Application State\n" +
                "Liveness: " + availability.getLivenessState() + "\n" +
                "Readiness" + availability.getReadinessState();
    }

    @GetMapping("/block")   //Readiness Block
    public String block() {
        AvailabilityChangeEvent.publish(eventPublisher, this, ReadinessState.REFUSING_TRAFFIC);
        return "Blocked requests " + localHostService.getLocalHostInfo();
    }

    @GetMapping("/turnoff")   //Liveness Block
    public String turnoff() {
        AvailabilityChangeEvent.publish(eventPublisher, this, LivenessState.BROKEN);
        return "Broken " + localHostService.getLocalHostInfo();
    }

    @Async
    @EventListener
    public void onStateChanged(AvailabilityChangeEvent<ReadinessState> readiness) throws InterruptedException {
        System.out.println("State is changed to " + readiness.getState());
        if(readiness.getState().equals(ReadinessState.REFUSING_TRAFFIC)) {
            AvailabilityChangeEvent.publish(eventPublisher, this, ReadinessState.ACCEPTING_TRAFFIC);
        }
//        Action When State Changed
    }
}
