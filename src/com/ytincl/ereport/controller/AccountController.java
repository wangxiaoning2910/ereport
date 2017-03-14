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

import com.ytincl.ereport.model.AccountRoleModel;
import com.ytincl.ereport.model.AccountsModel;
import com.ytincl.ereport.pojo.Account;
import com.ytincl.ereport.pojo.AccountRoles;
import com.ytincl.ereport.pojo.Role;
import com.ytincl.ereport.service.AccountRolesService;
import com.ytincl.ereport.service.AccountService;
import com.ytincl.ereport.service.RoleService;

@Controller
public class AccountController {

	private static Logger logger = LoggerFactory.getLogger(webappcontroller.class);
	@Autowired
	private AccountService accountService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private AccountRolesService accountRolesService;
	
	@RequestMapping(value = "/view/queryAccountList.do")
	@ResponseBody
	public AccountsModel queryAccountList(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<Account> accounts = accountService.queryAllAccount();
		ArrayList<Role> roles = (ArrayList<Role>) roleService.queryAllRole();
		AccountsModel am = new AccountsModel();
		ArrayList<AccountRoleModel> accountsModel = new ArrayList<AccountRoleModel>();
		String roleOfAccount;
		for(int i=0;i<accounts.size();i++){
			AccountRoleModel ar = new AccountRoleModel();
			roleOfAccount = "";
			System.out.println("1111111111111111"+accounts.get(i).getId());
			//根据accountId获取该账户拥有的角色
			ArrayList<Role> accountRoles = roleService.findRoleByAccount(accounts.get(i).getId());
			for(int j=0;j<accountRoles.size();j++){
				roleOfAccount += accountRoles.get(j).getName()+" ";
			}
			ar.setAccountName(accounts.get(i).getAccountName());
			ar.setRole(roleOfAccount);
			ar.setDescription(accounts.get(i).getDescription());
			ar.setState(accounts.get(i).getState());
			ar.setCreateTime(accounts.get(i).getCreateTime());
			accountsModel.add(ar);
		}
		am.setAccountRoleModel(accountsModel);
		am.setAccountList(accounts);
		am.setRoleList(roles);
		return am;
	}
	
	@RequestMapping(value = "/view/accRoleAdd.do")
	@ResponseBody
	public int accRoleAdd(HttpServletRequest request, HttpServletResponse response) {
		String accountName = request.getParameter("accountName");
		String roleKeys = request.getParameter("roleKeys");
		Account account = null;
		Role role = null;
		//根据姓名查找状态为1的账户
		account = accountService.getAccountByName(accountName);
		int acc_id = account.getId();
		AccountRoles accRole = new AccountRoles();
		int roleId = 0;
		int num = 0;
		accRole.setAcc_id(acc_id);
		if (roleKeys.indexOf(",") != -1) {
			String[] roleKey = roleKeys.split(",");
			for (int i = 0; i < roleKey.length; i++) {
				role = roleService.getRoleByRoleKey(roleKey[i]);
				roleId = role.getId();
				accRole.setRole_id(roleId );
				accountRolesService.insert(accRole);
			}
			return roleKey.length;
		} else {
			role = roleService.getRoleByRoleKey(roleKeys);
			roleId = role.getId();
			accRole.setRole_id(roleId );
			accountRolesService.insert(accRole);
			return 1;
		}
		
	}
	@RequestMapping(value = "/view/accountDelete.do")
	@ResponseBody
	public int accountDelete(HttpServletRequest request) {
		String accountNames = request.getParameter("accountName");
		if (accountNames.indexOf(",") != -1) {
			String[] accountName = accountNames.split(",");
			logger.debug("==========删除账号============");
			for (int i = 0; i < accountName.length; i++) {
				accountService.accountDelete(accountName[i]);
			}
			logger.debug("==========删除账号============");
			return accountName.length;
		} else {
			logger.debug("==========删除账号============");
			accountService.accountDelete(accountNames);
			logger.debug("==========删除账号============");
			return 1;
		}
	}
	@RequestMapping(value = "/view/accountAdd.do")
	@ResponseBody
	public int accountAdd(HttpServletRequest request) {
		String accountName = request.getParameter("accountNameAdd");
		String password = request.getParameter("passwordAdd");
		String description = request.getParameter("descriptionAdd");
		String state = request.getParameter("stateAdd");
		Account account = new Account();
		account.setAccountName(accountName);
		account.setPassword(password);
		account.setDescription(description);
		account.setState(state);
		accountService.accountAdd(account);
		return 0;
	}
	@RequestMapping(value = "/view/accountUpdate.do")
	@ResponseBody
	public int accountUpdate(HttpServletRequest request) throws UnsupportedEncodingException {
		String accountName = request.getParameter("accountNameUpdate");
		String password = request.getParameter("passwordUpdate");
		String description = request.getParameter("descriptionUpdate");
		String state = request.getParameter("stateUpdate");
		accountName = java.net.URLDecoder.decode(accountName, "UTF-8");
		description = java.net.URLDecoder.decode(description, "UTF-8");
		
		Account account = new Account();
		account.setAccountName(accountName);
		account.setPassword(password);
		account.setDescription(description);
		account.setState(state);
		int i = accountService.updateByName(account);
		return i;
	}
}
