package ar.edu.itba.paw.webapp.forms;

import ar.edu.itba.paw.models.Professor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

public class ContractForm {

    @NotEmpty()
    private String contractDescription;

    private boolean local;

    private boolean remote;

    @NotEmpty()@Pattern(regexp = "[0-9]+")
    private String price;


    public String getContractDescription() {
        return contractDescription;
    }

    public void setContractDescription(String contractDescription) {
        this.contractDescription = contractDescription;
    }

    public boolean isLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }

    public boolean isRemote() {
        return remote;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
