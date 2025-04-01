<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %><%--
  Created by IntelliJ IDEA.
  User: yanghwayeong
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%--%>
<%--  MemberRepository memberRepository = MemberRepository.getInstance();--%>
<%--  System.out.println("save.jsp");--%>
<%--  String username = request.getParameter("username");--%>
<%--  int age = Integer.parseInt(request.getParameter("age"));--%>
<%--  //Member 객체에 파라미터에서 가져온 값을 저장하고--%>
<%--  Member member = new Member(username,age);--%>

<%--  System.out.println("member = " + member);--%>
<%--  //객체를 회원저장소에 저장--%>
<%--  memberRepository.save(member);--%>


<%--%>--%>
<%-- 뷰 로직--%>
<html>
<head>
  <meta charset="UTF-8"></head>
<body>
성공
  <ul>

    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>

  </ul>
  <a href="/index.html">메인</a>
</body>
</html>

