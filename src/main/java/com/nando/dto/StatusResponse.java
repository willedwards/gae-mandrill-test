package com.nando.dto;/**
 * Author: wge
 * Date: 11/10/2013
 * Time: 19:14
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatusResponse
{

    private int statusCode = 0;
    private String message;
    private final List<String> listError = new ArrayList<>();
    private Object data;


    public int getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(int statusCode)
    {
        this.statusCode = statusCode;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public List<String> getListError()
    {
        return (listError == null) ? Collections.EMPTY_LIST : listError;
    }

    public void addError(String errorMessage)
    {
        listError.add(errorMessage);
    }

    public void setListError(List<String> allErrors)
    {
        listError.clear();
        if(allErrors !=null)
            listError.addAll(allErrors);
    }


    public Object getData()
    {
        return data;
    }

    public void setData(Object jsonObject)
    {
        this.data = jsonObject;
    }

    public StatusResponse()
    {
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof StatusResponse))
        {
            return false;
        }

        StatusResponse response = (StatusResponse) o;

        if (statusCode != response.statusCode)
        {
            return false;
        }
        if (data != null ? !data.equals(response.data) : response.data != null)
        {
            return false;
        }
        if (message != null ? !message.equals(response.message) : response.message != null)
        {
            return false;
        }
        if (listError != null ? !listError.equals(response.listError) : response.listError != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = statusCode;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (listError != null ? listError.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "StatusResponse{" +
               "statusCode=" + statusCode +
               ", errorMessage='" + message + '\'' +
               ", listError=" + listError +
               ", data=" + data +
               '}';
    }
}
