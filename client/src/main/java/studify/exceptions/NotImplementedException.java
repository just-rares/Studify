package studify.exceptions;

public class NotImplementedException extends Exception {
    String message;

    /**
     * Create a new error with a message.
     * This error signals that the feature is not implemented.
     *
     * @param message Message for the error
     */
    public NotImplementedException(String message) {
        this.message = message;
    }

    /**
     * Create a new error without a message.
     * This error signals that the feature is not implemented.
     */
    public NotImplementedException() {}

    /**
     * Prints the error of the message.
     *
     * @return Human representation of the error
     */
    public String toString() {
        if(this.message == null) {
            return "This method is not Implemented";
        }
        return "Not Implemented Error: " + this.message;
    }
}
