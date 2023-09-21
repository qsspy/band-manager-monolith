package com.qsspy.bands.query.application.members.port.input;

import com.qsspy.commons.architecture.cqrs.QueryHandler;

public interface GetBandMembersQueryHandler extends QueryHandler<GetBandMembersQuery, GetBandMembersQueryResult> {
}
