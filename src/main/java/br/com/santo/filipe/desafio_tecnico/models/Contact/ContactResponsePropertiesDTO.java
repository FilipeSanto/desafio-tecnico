package br.com.santo.filipe.desafio_tecnico.models.Contact;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ContactResponsePropertiesDTO {
    private String company;
    private ZonedDateTime createdate;
    private String email;
    private String firstname;
    private String hsAllContactVids;
    private String hsAssociatedTargetAccounts;
    private String hsCurrentlyEnrolledInProspectingAgent;
    private String hsEmailDomain;
    private String hsFullNameOrEmail;
    private String hsIsContact;
    private String hsIsUnworked;
    private ZonedDateTime hsLifecycleStageLeadDate;
    private String hsMarketableStatus;
    private String hsMarketableUntilRenewal;
    private String hsMembershipHasAccessedPrivateContent;
    private String hsObjectId;
    private String hsObjectSource;
    private String hsObjectSourceId;
    private String hsObjectSourceLabel;
    private String hsPipeline;
    private String hsProspectingAgentActivelyEnrolledCount;
    private String hsRegisteredMember;
    private String hsSearchableCalculatedPhoneNumber;
    private String hsSequencesActivelyEnrolledCount;
    private ZonedDateTime lastModifiedDate;
    private String lastname;
    private String lifecycleStage;
    private String numNotes;
    private String phone;
    private String website;
}
