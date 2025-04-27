package cm.amcloud.platform.controller;

import cm.amcloud.platform.dto.SubscriptionRequest;
import cm.amcloud.platform.model.Subscription;
import cm.amcloud.platform.service.BillingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping("/subscribe")
    public Subscription subscribe(@RequestBody SubscriptionRequest request) {
        return billingService.createSubscription(request.getAgencyName(), request.getPlanType(), 30);
    }

    @GetMapping("/validate")
    public boolean validate(@RequestParam String agencyName) {
        return billingService.checkSubscriptionValid(agencyName);
    }
}
