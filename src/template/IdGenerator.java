package template;

public abstract class IdGenerator {
	protected abstract String getPrefix();

    public String generateId(String latestId) {
        int newId;
        if (latestId == null || latestId.isEmpty()) {
            newId = 1;
        } else {
            int latestNumericId = Integer.parseInt(latestId.substring(1));
            newId = latestNumericId + 1;
        }
        return getPrefix() + String.format("%03d", newId);
    }
}
