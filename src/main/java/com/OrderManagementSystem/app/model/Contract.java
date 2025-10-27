package com.OrderManagementSystem.app.model;

import java.util.List;

public class Contract {
    String id;
    String name;
    String ContractTypeId;
    Status status;
    List<ContractLine> contractLines;

    public Contract(String id, String name, String contractTypeId, Status status, List<ContractLine> contracts) {
        this.id = id;
        this.name = name;
        ContractTypeId = contractTypeId;
        this.status = status;
        this.contractLines = contracts;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContractTypeId() {
        return ContractTypeId;
    }

    public Status getStatus() {
        return status;
    }

    public List<ContractLine> getContractLines() {
        return contractLines;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContractTypeId(String contractTypeId) {
        ContractTypeId = contractTypeId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setContractLines(List<ContractLine> contractLines) {
        this.contractLines = contractLines;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ContractTypeId='" + ContractTypeId + '\'' +
                ", status=" + status +
                ", contracts=" + contractLines +
                '}';
    }
}