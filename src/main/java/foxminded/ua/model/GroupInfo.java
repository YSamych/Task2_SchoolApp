package foxminded.ua.model;
public class GroupInfo {

    private final String groupName;
    private final Long amountOfStudents;

    public GroupInfo(String groupName, Long amountOfStudents) {
        this.groupName = groupName;
        this.amountOfStudents = amountOfStudents;
    }

    public String getGroupName() {
        return groupName;
    }

    public Long getAmountOfStudents() {
        return amountOfStudents;
    }

}
