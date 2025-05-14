import java.time.LocalDate;

public class TrainingTimes {
    private SvimmingDisciplin disciplin;
    private double timeInSeconds;
    private LocalDate date;

    public TrainingTimes(SvimmingDisciplin disciplin, double timeInSeconds, LocalDate date) {
        this.disciplin = disciplin;
        this.timeInSeconds = timeInSeconds;
        this.date = date;
    }

    public SvimmingDisciplin getDisciplin() {
        return disciplin;
    }

    public double getTimeInSeconds() {
        return timeInSeconds;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDisciplin(SvimmingDisciplin disciplin) {
        this.disciplin = disciplin;
    }

    public void setTimeInSeconds(double timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    // 🔧 Alias-metode til kompatibilitet med MenuUI
    public double getSvimTime() {
        return timeInSeconds;
    }

    // 🔧 Alias-metode til kompatibilitet med MenuUI
    public SvimmingDisciplin getSvimmingDisciplin() {
        return disciplin;
    }

    @Override
    public String toString() {
        return "Disciplin: " + disciplin + ", Tid: " + timeInSeconds + " sek, Dato: " + date;
    }
}
