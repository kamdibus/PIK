package pik.values.domain.variableModulePort;

import pik.values.domain.ValueRepository;

public class ValueVariableFacadeImpl implements ValueVariableFacade {

    private ValueRepository repository;

    public ValueVariableFacadeImpl(ValueRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteByVariable(String id) {
        repository.deleteByVariableId(id);
    }
}