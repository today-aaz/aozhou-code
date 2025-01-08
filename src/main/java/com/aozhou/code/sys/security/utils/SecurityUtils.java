//package com.aozhou.code.sys.security.utils;
//
//import com.aozhou.code.domain.dao.SysUser;
//import com.aozhou.code.exception.BusinessException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
///**
// * @Author: Aozhou
// * @Date: 2025/1/2
// */
//
///**
// * 安全服务（Security）工具类
// */
//public class SecurityUtils {
//        /**
//         * 用户ID
//         **/
//        public static Long getUserId()
//        {
//            try
//            {
//                return getLoginUser().getId();
//            }
//            catch (Exception e)
//            {
//                throw new BusinessException(401, "获取用户ID异常");
//            }
//        }
//
////        /**
////         * 获取部门ID
////         **/
////        public static Long getDeptId()
////        {
////            try
////            {
////                return getLoginUser().getDeptId();
////            }
////            catch (Exception e)
////            {
////                throw new ServiceException("获取部门ID异常", HttpStatus.UNAUTHORIZED);
////            }
////        }
//
//        /**
//         * 获取用户账户
//         **/
//        public static String getUsername()
//        {
//            try
//            {
//                return getLoginUser().getUsername();
//            }
//            catch (Exception e)
//            {
//                throw new BusinessException(401, "获取用户账户异常" );
//            }
//        }
//
//        /**
//         * 获取用户
//         **/
//        public static SysUser getLoginUser()
//        {
//            try
//            {
//                return (SysUser) getAuthentication().getPrincipal();
//            }
//            catch (Exception e)
//            {
//                throw new BusinessException(401, "获取用户信息异常");
//            }
//        }
//
//        /**
//         * 获取Authentication
//         */
//        public static Authentication getAuthentication()
//        {
//            return SecurityContextHolder.getContext().getAuthentication();
//        }
//
//        /**
//         * 生成BCryptPasswordEncoder密码
//         *
//         * @param password 密码
//         * @return 加密字符串
//         */
//        public static String encryptPassword(String password)
//        {
//            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            return passwordEncoder.encode(password);
//        }
//
//        /**
//         * 判断密码是否相同
//         *
//         * @param rawPassword 真实密码
//         * @param encodedPassword 加密后字符
//         * @return 结果
//         */
//        public static boolean matchesPassword(String rawPassword, String encodedPassword)
//        {
//            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            return passwordEncoder.matches(rawPassword, encodedPassword);
//        }
//
//        /**
//         * 是否为管理员
//         *
//         * @param userId 用户ID
//         * @return 结果
//         */
//        public static boolean isAdmin(Long userId)
//        {
//            if (userId != null){
//                return true;
//            }
//            return true;
//        }
//}
