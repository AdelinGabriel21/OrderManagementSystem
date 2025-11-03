package com.OrderManagementSystem.app.model;

import com.OrderManagementSystem.app.util.Ids;

import java.util.Date;
import java.util.List;

public class Contract {
    private String id;
    private String name;
    private String contractTypeId;
    private Status status;
    private List<ContractLine> contractLines;
    private Date creationDate;
    private Date expirationDate;

    public Contract(String name, String contractTypeId, Status status, List<ContractLine> contracts,
                    Date creationDate, Date expirationDate) {
        this.id = Ids.createId();
        this.name = name;
        this.contractTypeId = contractTypeId;
        this.status = status;
        this.contractLines = contracts;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
    }

    public Contract() {}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContractTypeId() {
        return contractTypeId;
    }

    public Status getStatus() {
        return status;
    }

    public List<ContractLine> getContractLines() {
        return contractLines;
    }

    public Date getCreationDate(){
        return creationDate;
    }

    public Date getExpirationDate(){
        return expirationDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContractTypeId(String contractTypeId) {
        contractTypeId = contractTypeId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setContractLines(List<ContractLine> contractLines) {
        this.contractLines = contractLines;
    }

    public void setCreationDate(Date creationDate){
        this.creationDate = creationDate;
    }

    public void setExpirationDate(Date expirationDate){
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ContractTypeId='" + contractTypeId + '\'' +
                ", status=" + status +
                ", contracts=" + contractLines +
                '}';
    }
}