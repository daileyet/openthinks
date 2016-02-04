-- phpMyAdmin SQL Dump
-- version 3.3.8.1
-- http://www.phpmyadmin.net
--
-- Host: w.rdc.sae.sina.com.cn:3307
-- Generation Time: Feb 04, 2016 at 03:28 PM
-- Server version: 5.6.23
-- PHP Version: 5.3.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `app_openthinks`
--

-- --------------------------------------------------------

--
-- Table structure for table `tasks`
--

CREATE TABLE IF NOT EXISTS `tasks` (
  `ID` char(12) NOT NULL,
  `Subject` varchar(500) DEFAULT NULL,
  `UID` int(11) NOT NULL,
  `CreateDate` varchar(45) DEFAULT NULL,
  `UpdateBy` varchar(45) DEFAULT NULL,
  `UpdateDate` varchar(45) DEFAULT NULL,
  `Lock` varchar(2) DEFAULT 'N',
  `LockedBy` varchar(45) DEFAULT NULL,
  `LockedDate` varchar(45) DEFAULT NULL,
  `TaskGroupID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_Tasks_Users1_idx` (`UID`),
  KEY `taskGroup_idx` (`TaskGroupID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



-- --------------------------------------------------------

--
-- Table structure for table `tasks_group`
--

CREATE TABLE IF NOT EXISTS `tasks_group` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `GroupName` varchar(100) NOT NULL,
  `Description` varchar(500) DEFAULT NULL,
  `CreatorID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

-- --------------------------------------------------------

--
-- Table structure for table `tasks_group_user`
--

CREATE TABLE IF NOT EXISTS `tasks_group_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TaskgroupID` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `taskGroupId_idx` (`TaskgroupID`),
  KEY `user_this_idx` (`UserID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

-- --------------------------------------------------------

--
-- Table structure for table `task_contents`
--

CREATE TABLE IF NOT EXISTS `task_contents` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TID` char(12) DEFAULT NULL,
  `Content` text,
  PRIMARY KEY (`ID`),
  KEY `ss_idx` (`TID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=78 ;


-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(45) DEFAULT NULL,
  `UserPassword` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

