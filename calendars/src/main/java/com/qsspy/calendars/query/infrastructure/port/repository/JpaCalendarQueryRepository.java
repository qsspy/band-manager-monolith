package com.qsspy.calendars.query.infrastructure.port.repository;

import com.qsspy.calendars.query.application.entries.details.port.output.dto.CalendarEntryDetailsDTO;
import com.qsspy.calendars.query.application.entries.list.port.output.dto.CalendarEntryDTO;
import com.qsspy.calendars.query.application.membersrestrictions.port.output.dto.CalendarEntryMemberRestrictionDTO;
import com.qsspy.domain.calendar.CalendarEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
interface JpaCalendarQueryRepository extends JpaRepository<CalendarEntry, UUID> {

    @Query("""
           SELECT new com.qsspy.calendars.query.application.membersrestrictions.port.output.dto.CalendarEntryMemberRestrictionDTO(
                c.id.value,
                c.eventKind,
                c.eventDate.value,
                u.id,
                u.email,
                p.canSeeCalendarEntry.isAllowed,
                p.canSeeCalendarEntryPayment.isAllowed,
                p.canSeeCalendarEntryDetails.isAllowed
           )
           FROM CALENDAR_ENTRIES c
           INNER JOIN USERS u ON c.bandId.value = u.memberBand.id.value
           LEFT JOIN RESTRICTED_CALENDAR_ENTRY_VIEWER_PRIVILEGES p ON u.id = p.id.memberId AND c.id.value = p.id.entryId
           WHERE c.bandId.value = :bandId
           """)
    List<CalendarEntryMemberRestrictionDTO> getCalendarEntriesUserRestrictions(final UUID bandId);

    @Query("""
           SELECT new com.qsspy.calendars.query.application.entries.details.port.output.dto.CalendarEntryDetailsDTO(
                CE.address.fullAddress,
                CE.eventDuration.value,
                CE.description.text
           )
           FROM CALENDAR_ENTRIES CE
           INNER JOIN BANDS B ON B.id.value = CE.bandId.value
           LEFT JOIN USERS U1 ON U1.id = B.adminUser.id
           LEFT JOIN USERS U2 ON U2.memberBand.id.value = B.id.value
           LEFT JOIN RESTRICTED_CALENDAR_ENTRY_VIEWER_PRIVILEGES EP on EP.id.memberId = U2.id AND EP.id.entryId = CE.id.value
           INNER JOIN DEFAULT_BAND_PRIVILEGES DP ON DP.id.value = B.id.value
           WHERE B.id.value = :bandId
               AND CE.id.value = :entryId
               AND
                    (
                        U1.id = :memberId
                        OR
                        (
                           U2.id = :memberId
                           AND
                           CASE
                                WHEN EP.canSeeCalendarEntryDetails IS NULL THEN DP.canSeeCalendarEntryDetailsByDefault.isAllowed
                                ELSE EP.canSeeCalendarEntryDetails.isAllowed
                           END
                        )
                    )
           """)
    Optional<CalendarEntryDetailsDTO> findCalendarEntryDetailsByBandIdAndMemberIdAndEntryId(final UUID bandId, final UUID memberId, final UUID entryId);

    @Query("""
           SELECT new com.qsspy.calendars.query.application.entries.list.port.output.dto.CalendarEntryDTO(
                CE.id.value,
                CE.eventKind,
                CE.eventDate.value,
                CE.amount.value,
                EP.canSeeCalendarEntryDetails.isAllowed,
                DP.canSeeCalendarEntryDetailsByDefault.isAllowed,
                EP.canSeeCalendarEntryPayment.isAllowed,
                DP.canSeeCalendarEntryPaymentByDefault.isAllowed
           )
           FROM CALENDAR_ENTRIES CE
           INNER JOIN BANDS B ON B.id.value = CE.bandId.value
           LEFT JOIN USERS U1 ON U1.id = B.adminUser.id
           LEFT JOIN USERS U2 ON U2.memberBand.id.value = B.id.value
           LEFT JOIN RESTRICTED_CALENDAR_ENTRY_VIEWER_PRIVILEGES EP on EP.id.memberId = U2.id AND EP.id.entryId = CE.id.value
           INNER JOIN DEFAULT_BAND_PRIVILEGES DP ON DP.id.value = B.id.value
           WHERE B.id.value = :bandId
               AND
                    (
                        U1.id = :memberId
                        OR
                        (
                           U2.id = :memberId
                           AND
                           CASE
                                WHEN EP.canSeeCalendarEntry IS NULL THEN DP.canSeeCalendarEntryByDefault.isAllowed
                                ELSE EP.canSeeCalendarEntry.isAllowed
                           END
                        )
                    )
           """)
    List<CalendarEntryDTO> getCalendarEntriesByBandIdAndMemberId(final UUID bandId, final UUID memberId);
}
