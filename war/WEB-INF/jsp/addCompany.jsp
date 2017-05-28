<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <div id="container">
  	  <div id="wb_Heading1" style="position:absolute;left:14px;top:114px;width:1148px;z-index:15;">
         <c:choose>
	          <c:when test="${add}">
	            <h1 id="Heading1">Добавить компанию</h1><br>
	          </c:when>
	          <c:otherwise>
	          	<h1 id="Heading1">Изменить название компании</h1><br>
	          </c:otherwise>
	        </c:choose>
	        <br>
         <form:form modelAttribute="comp" method="post" style="top:115px">
            Название<br>
            <form:input path ="name" type="text" id="Editbox1" style="margin-top:6px;width:1138px;height:27px;line-height:27px;z-index:4;" name="Editbox1" value="" spellcheck="false"/><br>
            <form:errors path="name" cssClass="errors" style="font-size:12px;color:red;width:80%;z-index:3;"/>
            <p align="right">
            <c:choose>
	          <c:when test="${add}">
	            <input type="submit" name="add" value="Добавить" style="width:172px;height:48px;font-size:17px">
	          </c:when>
	          <c:otherwise>
	          	<form:input type="hidden" path="id"/>
	            <input type="submit" name="save" value="Сохранить" style="width:172px;height:48px;font-size:17px">
	          </c:otherwise>
	        </c:choose>
	        </p>
	     </form:form>
      </div>
   </div>
  
<%@ include file="/WEB-INF/jsp/footer.jsp" %>