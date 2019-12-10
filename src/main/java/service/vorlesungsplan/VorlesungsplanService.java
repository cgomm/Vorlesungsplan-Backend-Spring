package service.vorlesungsplan;

import org.springframework.stereotype.Service;

@Service("VorlesungsplanService")
public class VorlesungsplanService {
    private String searchterm;

    public void setSearchterm(String searchterm) {
        this.searchterm = searchterm;
    }

    public String getSearchterm() {
        return searchterm;
    }
}
