package pages.ResultsStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResultsStorage {

    public String getPhase1Output() {
        return phase1Output;
    }

    public void setPhase1Output(String phase1Output) {
        this.phase1Output = phase1Output;
    }

    public String getPhase2Output() {
        return phase2Output;
    }

    public void setPhase2Output(String phase2Output) {
        this.phase2Output = phase2Output;
    }

    @Autowired
    public String phase1Output;

    @Autowired
    public String phase2Output;

}
