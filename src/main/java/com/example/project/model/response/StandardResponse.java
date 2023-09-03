package com.example.project.model.response;

public class StandardResponse extends AbstractResponse{

      /** Return Standard Response with OK Status.
            * @return
            */
    public static StandardResponse OK() {
        return (StandardResponse) new StandardResponse().initSuccess( "200", "Request Completed!");
    }

    /**
     * Returns Standard Response with ERROR Status
     * @return
     */
    public static StandardResponse ERROR() {
        return (StandardResponse) new StandardResponse().initFailure( "400", "Request Failed!");
    }

}
