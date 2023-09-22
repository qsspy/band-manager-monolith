package com.qsspy.calendars.query.infrastructure.port.repository;

import com.qsspy.calendars.query.application.entries.details.port.output.dto.CalendarEntryDetailsDTO;
import com.qsspy.calendars.query.application.entries.list.port.output.dto.CalendarEntryDTO;
import com.qsspy.calendars.query.application.membersrestrictions.port.output.dto.CalendarEntryMemberRestrictionDTO;
import com.qsspy.jpadatamodel.entity.CalendarEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
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

    @Query("""
           SELECT new com.qsspy.calendars.query.application.entries.details.port.output.dto.CalendarEntryDetailsDTO(
                CE.address,
                CE.eventDuration,
                CE.description
           )
           FROM CALENDAR_ENTRIES CE
           INNER JOIN USERS U ON CE.bandId = U.memberBand.id OR CE.bandId = U.ownedBand.id
           WHERE CE.id = :entryId
                 AND CE.bandId = :bandId
                 AND U.id = :memberId
                 AND
                 (
                     U.ownedBand.id = :bandId
                     OR
                     CASE WHEN (SELECT EP.canSeeCalendarEntryDetails
                                       FROM RESTRICTED_CALENDAR_ENTRY_VIEWER_PRIVILEGES EP
                                       WHERE EP.entryId = CE.id AND EP.memberId = U.id) IS NULL THEN (SELECT BP.canSeeCalendarEntryDetailsByDefault
                                                                                                      FROM DEFAULT_BAND_PRIVILEGES BP
                                                                                                      WHERE BP.id = CE.bandId)
                          ELSE (SELECT EP.canSeeCalendarEntryDetails
                                       FROM RESTRICTED_CALENDAR_ENTRY_VIEWER_PRIVILEGES EP
                                       WHERE EP.entryId = CE.id AND EP.memberId = U.id)
                     END
                 )
           """)
    Optional<CalendarEntryDetailsDTO> findCalendarEntryDetailsByBandIdAndMemberIdAndEntryId(final UUID bandId, final UUID memberId, final UUID entryId);

    @Query("""
           SELECT new com.qsspy.calendars.query.application.entries.list.port.output.dto.CalendarEntryDTO(
                CE.id,
                CE.eventKind,
                CE.eventDate,
                CE.amount,
                EP.canSeeCalendarEntryDetails,
                DP.canSeeCalendarEntryDetailsByDefault,
                EP.canSeeCalendarEntryPayment,
                DP.canSeeCalendarEntryPaymentByDefault
           )
           FROM CALENDAR_ENTRIES CE
           INNER JOIN USERS U ON U.ownedBand.id = CE.bandId OR U.memberBand.id = CE.bandId
           LEFT JOIN RESTRICTED_CALENDAR_ENTRY_VIEWER_PRIVILEGES EP on EP.memberId = U.id AND EP.entryId = CE.id
           INNER JOIN DEFAULT_BAND_PRIVILEGES DP ON DP.band.id = CE.bandId
           WHERE CE.bandId = :bandId
               AND U.id = :memberId
               AND
                   (
                       U.ownedBand.id = CE.bandId
                       OR
                       CASE
                           WHEN EP.canSeeCalendarEntry IS NULL THEN DP.canSeeCalendarEntryByDefault
                           ELSE EP.canSeeCalendarEntry
                       END
                   )
           """)
    List<CalendarEntryDTO> getCalendarEntriesByBandIdAndMemberId(final UUID bandId, final UUID memberId);

//    SELECT *
//    FROM CALENDAR_ENTRIES AS CE
//    INNER JOIN USERS AS U ON U.OWN_BAND_ID = CE.BAND_ID OR U.MEMBER_BAND_ID = CE.BAND_ID
//    LEFT JOIN RESTRICTED_CALENDAR_ENTRY_VIEWER_PRIVILEGES AS EP ON EP.MEMBER_ID = U.ID AND EP.ENTRY_ID = CE.ID
//    INNER JOIN DEFAULT_BAND_PRIVILEGES AS DP ON DP.BAND_ID = CE.BAND_ID
//    WHERE CE.BAND_ID = '7a190c1b-3e87-4e62-a983-fc2083eef461'
//    AND U.ID = '7a190c1b-3e87-4e62-a983-fc2083eef471'
//    AND
//            (
//                    U.OWN_BAND_ID = CE.BAND_ID
//                    OR
//                    CASE
//                    WHEN EP.CAN_SEE_ENTRY IS NULL THEN DP.CAN_SEE_CALENDAR_ENTRY_BY_DEFAULT
//                    ELSE EP.CAN_SEE_ENTRY
//                    END
//            )
}
