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
           INNER JOIN USERS U ON CE.bandId.value = U.memberBand.id.value OR CE.bandId.value = U.ownedBand.id.value
           WHERE CE.id.value = :entryId
                 AND CE.bandId.value = :bandId
                 AND U.id = :memberId
                 AND
                 (
                     U.ownedBand.id.value = :bandId
                     OR
                     CASE WHEN (SELECT EP.canSeeCalendarEntryDetails
                                FROM RESTRICTED_CALENDAR_ENTRY_VIEWER_PRIVILEGES EP
                                WHERE EP.id.entryId = CE.id.value AND EP.id.memberId = U.id) IS NULL THEN (SELECT BP.canSeeCalendarEntryDetailsByDefault.isAllowed
                                                                                                      FROM DEFAULT_BAND_PRIVILEGES BP
                                                                                                      WHERE BP.id.value = CE.bandId.value)
                          ELSE (SELECT EP.canSeeCalendarEntryDetails.isAllowed
                                FROM RESTRICTED_CALENDAR_ENTRY_VIEWER_PRIVILEGES EP
                                WHERE EP.id.entryId = CE.id.value AND EP.id.memberId = U.id)
                     END
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
           INNER JOIN USERS U ON U.ownedBand.id.value = CE.bandId.value OR U.memberBand.id.value = CE.bandId.value
           LEFT JOIN RESTRICTED_CALENDAR_ENTRY_VIEWER_PRIVILEGES EP on EP.id.memberId = U.id AND EP.id.entryId = CE.id.value
           INNER JOIN DEFAULT_BAND_PRIVILEGES DP ON DP.id.value = CE.bandId.value
           WHERE CE.bandId.value = :bandId
               AND U.id = :memberId
               AND
                   (
                       U.ownedBand.id.value = CE.bandId.value
                       OR
                       CASE
                           WHEN EP.canSeeCalendarEntry IS NULL THEN DP.canSeeCalendarEntryByDefault.isAllowed
                           ELSE EP.canSeeCalendarEntry.isAllowed
                       END
                   )
           """)
    List<CalendarEntryDTO> getCalendarEntriesByBandIdAndMemberId(final UUID bandId, final UUID memberId);
}
