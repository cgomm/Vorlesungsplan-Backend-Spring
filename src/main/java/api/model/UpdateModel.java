package api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Entity
public class UpdateModel {
    @Id
    private Date lastUpdate;

    public UpdateModel() {
        this.lastUpdate = new Date();
    }
}
