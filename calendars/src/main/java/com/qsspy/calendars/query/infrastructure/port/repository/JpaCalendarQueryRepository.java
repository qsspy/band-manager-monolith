package com.qsspy.calendars.query.infrastructure.port.repository;

import com.qsspy.calendars.query.application.membersrestrictions.port.output.dto.CalendarEntryMemberRestrictionDTO;
import com.qsspy.jpadatamodel.entity.CalendarEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface JpaCalendarQueryRepository extends JpaRepository<CalendarEntryEntity, UUID> {

    @Query("""
           SELECT new com.qsspy.calendars.query.application.membersrestrictions.port.output.dto.CalendarEntryMemberRestrictionDTO(
                c.id,
                c.eventKind,
                c.eventDate,
                u.id,
                u.email,
                p.canSeeCalendarEntry,
                p.canSeeCalendarEntryPayment,
                p.canSeeCalendarEntryDetails
           )
           FROM CALENDAR_ENTRIES c
           INNER JOIN USERS u ON c.bandId = u.memberBand.id
           LEFT JOIN RESTRICTED_CALENDAR_ENTRY_VIEWER_PRIVILEGES p ON u.id = p.memberId AND c.id = p.entryId
           WHERE c.bandId = :bandId
           """)
    List<CalendarEntryMemberRestrictionDTO> getCalendarEntriesUserRestrictions(final UUID bandId);
}
