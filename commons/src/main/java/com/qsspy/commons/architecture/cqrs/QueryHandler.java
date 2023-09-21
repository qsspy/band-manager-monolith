package com.qsspy.commons.architecture.cqrs;

public interface QueryHandler<I extends Query, O extends QueryResult> {

    /**
     * Handles the query
     *
     * @param query query to be handled
     * @return query result
     */
    O handle(final I query);
}
