package com.school.network;

public class Request implements java.io.Serializable
{
private String entity;
private String operation;
private Object object;

public Request()
{
this.entity="";
this.operation="";
this.object=null;
}

public Request(String entity,String operation,Object object)
{
this.entity=entity;
this.operation=operation;
this.object=object;
}

public String getEntity()
{
return this.entity;
}

public void setEntity(String entity)
{
this.entity=entity;
}

public String getOperation()
{
return this.operation;
}

public void setOperation(String operation)
{
this.operation=operation;
}

public Object getObject()
{
return this.object;
}

public void setObject(Object object)
{
this.object=object;
}

}