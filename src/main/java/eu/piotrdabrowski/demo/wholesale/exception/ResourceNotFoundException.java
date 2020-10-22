package eu.piotrdabrowski.demo.wholesale.exception;

public class ResourceNotFoundException extends RuntimeException {

    private final String message;

    public ResourceNotFoundException(Class<?> resource, Long id) {
        this.message = String.format("Cannot find %s with id= %s", resource.getName(), id);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
