package com.ytincl.ereport.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ytincl.ereport.model.AccountsModel;
import com.ytincl.ereport.pojo.Account;
import com.ytincl.ereport.pojo.AccountRoles;
import com.ytincl.ereport.pojo.Resources;
import com.ytincl.ereport.pojo.ResourcesRole;
import com.ytincl.ereport.pojo.Role;
import com.ytincl.ereport.service.AccountService;
import com.ytincl.ereport.service.ResourcesRolesService;
import com.ytincl.ereport.service.ResourcesService;
import com.ytincl.ereport.service.RoleService;

@Controller
public class RoleController {

	private static Logger logger = LoggerFactory.getLogger(webappcontroller.class);
	@Autowired
	private RoleService roleService;
	@Autowired
	private ResourcesService resourcesService;
	@Autowired
	private ResourcesRolesService resourcesRolesService;
	
	@RequestMapping(value = "/view/queryRoleList.do")
	@ResponseBody
	public AccountsModel queryRoleList(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<Role> roles = (ArrayList<Role>) roleService.queryAllRole();
		AccountsModel am = new AccountsModel();
		am.setRoleList(roles);
		return am;
	}
	@RequestMapping(value = "/view/queryResourceList.do")
	@ResponseBody
	public AccountsModel queryResourceList(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<Resources> resourcesList = (ArrayList<Resources>) resourcesService.getAllResources();
		AccountsModel am = new AccountsModel();
		am.setResources(resourcesList);
		return am;
	}
	@RequestMapping(value = "/view/roleDelete.do")
	@ResponseBody
	public int accountDelete(HttpServletRequest request) {
		String names = request.getParameter("name");
		try {
			names = java.net.URLDecoder.decode(names, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (names.indexOf(",") != -1) {
			String[] name = names.split(",");
			logger.debug("==========É¾³ýÕËºÅ============");
			for (int i = 0; i < name.length; i++) {
				roleService.roleDelete(name[i]);
			}
			logger.debug("==========É¾³ýÕËºÅ============");
			return name.length;
		} else {
			logger.debug("==========É¾³ýÕËºÅ============");
			roleService.roleDelete(names);
			logger.debug("==========É¾³ýÕËºÅ============");
			return 1;
		}
	}
	@RequestMapping(value = "/view/roleAdd.do")
	@ResponseBody
	public int roleAdd(HttpServletRequest request) {
		String name = request.getParameter("roleNameAdd");
		String roleKey = request.getParameter("roleKeyAdd");
		String description = request.getParameter("descriptionAdd");
		String enable = request.getParameter("enableAdd");
		try {
			name = java.net.URLDecoder.decode(name, "UTF-8");
			description = java.net.URLDecoder.decode(description, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Role role = new Role();
		role.setDescription(description);
		role.setEnable(Integer.parseInt(enable));//±ØÐëÊÇÊý×Ö²»È»±¨´í
		role.setName(name);
		role.setRoleKey(roleKey);
		roleService.roleAdd(role);
		return 0;
	}
	@RequestMapping(value = "/view/roleUpdate.do")
	@ResponseBody
	public int roleUpdate(HttpServletRequest request) throws UnsupportedEncodingException {
		String name = request.getParameter("roleNameUpdate");
		String roleKey = request.getParameter("roleKeyUpdate");
		String description = request.getParameter("descriptionUpdate");
		String enable = request.getParameter("enableUpdate");
		name = java.net.URLDecoder.decode(name, "UTF-8");
		description = java.net.URLDecoder.decode(description, "UTF-8");
		
		Role role = new Role();
		role.setName(name);
		role.setRoleKey(roleKey);
		role.setDescription(description);
		role.setEnable(Integer.parseInt(enable));
		int i = roleService.updateByName(role);
		return i;
	}
	
	@RequestMapping(value = "/view/roleResAdd.do")
	@ResponseBody
	public int roleResAdd(HttpServletRequest request, HttpServletResponse response) {
		String roleResName = request.getParameter("roleResName");
		Role role = null;
		role = roleService.getRoleByName(roleResName);
		int role_id = role.getId();
		String mids = request.getParameter("mids");
		ResourcesRole resourcesRole = new ResourcesRole();
		Resources resouces = new Resources();
		int num = 0;
		int res_id = 0;
		resourcesRole.setRole_id(role_id);
		if (mids.indexOf(",") != -1) {
			String[] mid = mids.split(",");
			for (int i = 0; i < mid.length; i++) {
				resouces = resourcesService.getResourcesByMid(mid[i]);
				res_id = resouces.getId();
				resourcesRole.setResc_id(res_id);
				resourcesRolesService.insert(resourcesRole);
			}
			return mid.length;
		} else {
			resouces = resourcesService.getResourcesByMid(mids);
			res_id = resouces.getId();
			resourcesRole.setResc_id(res_id);
			resourcesRolesService.insert(resourcesRole);
			return 1;
		}
		
	}
}
