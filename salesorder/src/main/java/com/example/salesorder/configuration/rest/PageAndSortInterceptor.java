/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.configuration.rest;

import com.github.bohnman.squiggly.web.SquigglyRequestFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author joao
 */
public class PageAndSortInterceptor implements HandlerInterceptor {

    private CustomPageRequest customPageRequest;

    @Autowired
    public PageAndSortInterceptor(CustomPageRequest customPageRequest) {
        this.customPageRequest = customPageRequest;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String paramPage = request.getParameter("page");
        String paramSize = request.getParameter("size");
        String paramOrderField = request.getParameter("orderField");
        String paramSort = request.getParameter("sort");

        Integer pageNumber = (paramPage == null || paramPage.isEmpty())
                ? null : Integer.parseInt(paramPage);
        Integer pageSize = (paramSize == null || paramSize.isEmpty())
                ? null : Integer.parseInt(paramSize);
        String orderField = (paramOrderField == null || paramOrderField.isEmpty())
                ? null : paramOrderField;

        Sort.Direction direction = null;
        if (paramSort != null && paramSort.isEmpty()) {
            switch (paramSort) {
                case "asc":
                    direction = Sort.Direction.ASC;
                    break;
                case "desc":
                    direction = Sort.Direction.DESC;
                    break;
                default:
                    break;
            }
        }

        customPageRequest = new CustomPageRequestBuilder()
                .withPageNumber(pageNumber)
                .withPageSize(pageSize)
                .withOrderField(orderField)
                .withOrder(direction)
                .build();

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) throws Exception {
    }

    public CustomPageRequest getCustomPageRequest() {
        return customPageRequest;
    }

    public void setCustomPageRequest(CustomPageRequest customPageRequest) {
        this.customPageRequest = customPageRequest;
    }
}
