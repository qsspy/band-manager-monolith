package com.qsspy.finances.command.application.addition;

import com.qsspy.domain.finances.FinanceEntryFactory;
import com.qsspy.finances.command.application.addition.port.input.AddFinanceEntryCommand;
import com.qsspy.finances.command.application.addition.port.input.AddFinanceEntryCommandHandler;
import com.qsspy.finances.command.application.addition.port.output.FinanceEntrySaveRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
class AddFinanceEntryCommandHandlerImpl implements AddFinanceEntryCommandHandler {

    private final FinanceEntrySaveRepository repository;

    @Override
    public void handle(final AddFinanceEntryCommand command) {
        command.validate();

        if(!command.initiatorsBandId().equals(command.bandId())) {
            throw new IllegalStateException("Member can only add entries to its own band!");
        }

        final var specification = CommandToDtoMapper.toSpecification(command);
        final var entry = FinanceEntryFactory.createNewFinanceEntry(specification);
        repository.save(entry);
    }
}
