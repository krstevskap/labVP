//package mk.ukim.finki.lab.web.filter;
//
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter
//public class CourseFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
////        Long courseId = (Long) request.getSession().getAttribute("courseId");
////        String course = request.getParameter("course");
////        String path = request.getServletPath();
//
////        if (path.startsWith("/courses")) {
////            filterChain.doFilter(request, response);
////            return;
////        }
////
////        if (courseId == null && course == null) {
////            response.sendRedirect("/courses");
////        } else {
////            filterChain.doFilter(request, response);
////        }
//        if(!request.getServletPath().contains("/courses") && request.getSession().getAttribute("courseId") == null){
//            response.sendRedirect("/courses");
//        }
//        else{
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
