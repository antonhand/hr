<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <div id="container">
  	  <div id="wb_Heading1" style="position:absolute;left:14px;top:114px;width:810px;height:50px;z-index:15;">
         <c:choose>
	          <c:when test="${add}">
	            <h1 id="Heading1">Добавить работника</h1>
	          </c:when>
	          <c:otherwise>
	          	<h1 id="Heading1">Изменить данные работника</h1>
	          </c:otherwise>
	        </c:choose>
      </div>
      <div id="wb_Form1" style="position:absolute;left:0px;top:164px;width:1200px;height:374px;z-index:16;">
         <form:form modelAttribute="emp" method="post">
            <label for="Editbox1" id="Label1" style="position:absolute;left:56px;top:19px;width:76px;height:22px;line-height:22px;z-index:3;">Фамилия</label>
            <form:input path ="surname" type="text" id="Editbox1" style="position:absolute;left:151px;top:16px;width:1016px;height:27px;line-height:27px;z-index:4;" name="Editbox1" value="" spellcheck="false"/>
            <form:errors path="surname" cssClass="errors" style="position:absolute;left:151px;top:55px;font-size:12px;color:red;width:80%;z-index:3;"/>
            <label for="Editbox2" id="Label2" style="position:absolute;left:94px;top:75px;width:38px;height:22px;line-height:22px;z-index:5;">Имя</label>
            <label for="Editbox3" id="Label3" style="position:absolute;left:43px;top:131px;width:89px;height:22px;line-height:22px;z-index:6;">Отчество</label>
            <form:select items="${educations}" path ="education.id" name="Combobox1" size="1" id="Combobox1" style="position:absolute;left:151px;top:184px;width:1026px;height:37px;z-index:7;"/>
            <label for="Editbox4" id="Label5" style="position:absolute;left:76px;top:243px;width:56px;height:22px;line-height:22px;z-index:8;">Адрес</label>
            <label for="Combobox1" id="Label4" style="position:absolute;left:14px;top:187px;width:118px;height:22px;line-height:22px;z-index:9;">Образование</label>
            <form:input path ="name" type="text" id="Editbox2" style="position:absolute;left:151px;top:72px;width:1016px;height:27px;line-height:27px;z-index:10;" name="Editbox1" value="" spellcheck="false"/>
            <form:errors path="name" cssClass="errors" style="position:absolute;left:151px;top:112px;font-size:12px;color:red;width:80%;z-index:3;"/>
            <form:input path ="midname" type="text" id="Editbox3" style="position:absolute;left:151px;top:128px;width:1016px;height:27px;line-height:27px;z-index:11;" name="Editbox1" value="" spellcheck="false"/>
            <form:input path ="address" type="text" id="Editbox4" style="position:absolute;left:151px;top:240px;width:1016px;height:27px;line-height:27px;z-index:12;" name="Editbox1" value="" spellcheck="false"/>
            <c:choose>
	          <c:when test="${add}">
	            <input type="submit" name="add" value="Добавить" style="position:absolute;left:1005px;top:295px;width:172px;height:48px;font-size:17px">
	          </c:when>
	          <c:otherwise>
	          	<form:input type="hidden" path="id"/>
	            <input type="submit" name="save" value="Сохранить" style="position:absolute;left:1005px;top:295px;width:172px;height:48px;font-size:17px">
	          </c:otherwise>
	        </c:choose>
         </form:form>
      </div>
   </div>
  
<%@ include file="/WEB-INF/jsp/footer.jsp" %>