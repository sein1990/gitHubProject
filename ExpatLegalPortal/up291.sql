-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 08, 2015 at 12:45 PM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `recruitment`
--

-- --------------------------------------------------------

--
-- Table structure for table `academic`
--

CREATE TABLE IF NOT EXISTS `academic` (
  `username` varchar(100) NOT NULL,
  `certificate` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `academic`
--

INSERT INTO `academic` (`username`, `certificate`) VALUES
('14011006', 'IMG-20160715-WA0016.jpg'),
('hh', 'vvvv');

-- --------------------------------------------------------

--
-- Table structure for table `apply`
--

CREATE TABLE IF NOT EXISTS `apply` (
`appid` int(100) NOT NULL,
  `job_id` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL,
  `organization` varchar(100) NOT NULL,
  `number_of_post` varchar(100) NOT NULL,
  `moredetails` varchar(100) NOT NULL,
  `deadline` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `certificate` varchar(100) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=38 ;

--
-- Dumping data for table `apply`
--

INSERT INTO `apply` (`appid`, `job_id`, `description`, `organization`, `number_of_post`, `moredetails`, `deadline`, `username`, `certificate`) VALUES
(31, '53', 'CIVIL ENG', 'DIT', '1', 'ggg.jpg', '2017-05-15', '14011006', 'IMG-20160715-WA0016.jpg'),
(32, '51', 'cccccccc', 'jhhhh', '5', 'Challenges-Opportunities-for-Organisational-Behaviour.pdf', '65', '14011006', 'IMG-20160715-WA0016.jpg'),
(33, '54', 'Executive Director', 'NMB', '1', 'Capture.jpg', '2017-05-22', 'admin', ''),
(34, '50', 'vvvvvvvvv', 'ccc', '2', '21MISC2012OB0921201246.pdf', 'ff', '14011006', 'IMG-20160715-WA0016.jpg'),
(35, '57', 'Human Resource Manger', 'UAUT', '1', '192.docx', '2017-07-08', '14011006', 'IMG-20160715-WA0016.jpg'),
(36, '58', 'human rights ', 'ifm', '1', 'ooooooo.txt', '12/3/09', '14011006', 'IMG-20160715-WA0016.jpg'),
(37, '56', 'KAAYA', 'DIT', '2', '3207546.pdf', '2017-06-22', '14011006', 'IMG-20160715-WA0016.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `call`
--

CREATE TABLE IF NOT EXISTS `call` (
  `appid` int(100) NOT NULL,
  `job_id` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL,
  `organization` varchar(100) NOT NULL,
  `number_of_post` varchar(100) NOT NULL,
  `moredetails` varchar(100) NOT NULL,
  `interview_date` date NOT NULL,
  `time` varchar(100) NOT NULL,
  `location` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `call`
--

INSERT INTO `call` (`appid`, `job_id`, `description`, `organization`, `number_of_post`, `moredetails`, `interview_date`, `time`, `location`, `username`) VALUES
(31, '53', 'CIVIL ENG', 'DIT', '1', 'ggg.jpg', '0000-00-00', '', '', '14011006'),
(32, '51', 'cccccccc', 'jhhhh', '5', 'Challenges-Opportunities-for-Organisational-Behaviour.pdf', '2017-05-15', '5', '5', '14011006'),
(34, '50', 'vvvvvvvvv', 'ccc', '2', '21MISC2012OB0921201246.pdf', '0000-00-00', '', '', '14011006'),
(35, '57', 'Human Resource Manger', 'UAUT', '1', '192.docx', '2017-07-10', '12;00', 'UAUT', '14011006');

-- --------------------------------------------------------

--
-- Table structure for table `profile`
--

CREATE TABLE IF NOT EXISTS `profile` (
  `username` varchar(100) NOT NULL,
  `fname` varchar(100) NOT NULL,
  `sname` varchar(100) NOT NULL,
  `lname` varchar(100) NOT NULL,
  `e_mail` varchar(100) NOT NULL,
  `phoneNumber` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `profile`
--

INSERT INTO `profile` (`username`, `fname`, `sname`, `lname`, `e_mail`, `phoneNumber`) VALUES
('14011006', '', '', '', '', ''),
('admin', '', '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `userType` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `userType`) VALUES
('14011006', '1234', 'applicant'),
('140110065', '1234', 'applicant'),
('3333333', '2', 'applicant'),
('admin', '1234', 'admin'),
('s', 's', 'applicant');

-- --------------------------------------------------------

--
-- Table structure for table `vacation`
--

CREATE TABLE IF NOT EXISTS `vacation` (
`job_id` int(100) NOT NULL,
  `description` varchar(100) NOT NULL,
  `organization` varchar(100) NOT NULL,
  `number_of_post` varchar(100) NOT NULL,
  `moredetails` varchar(100) NOT NULL,
  `deadline` varchar(100) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=59 ;

--
-- Dumping data for table `vacation`
--

INSERT INTO `vacation` (`job_id`, `description`, `organization`, `number_of_post`, `moredetails`, `deadline`) VALUES
(50, 'vvvvvvvvv', 'ccc', '2', '21MISC2012OB0921201246.pdf', 'ff'),
(51, 'cccccccc', 'jhhhh', '5', 'Challenges-Opportunities-for-Organisational-Behaviour.pdf', '65'),
(52, 'cccccccc', 'ccc', '5', '', '65'),
(53, 'CIVIL ENG', 'DIT', '1', 'ggg.jpg', '2017-05-15'),
(54, 'Executive Director', 'NMB', '1', 'Capture.jpg', '2017-05-22'),
(55, 'CIVIL ENG', 'UAUT', '1', '32.Allani.pdf', '2017-06-30'),
(56, 'KAAYA', 'DIT', '2', '3207546.pdf', '2017-06-22'),
(57, 'Human Resource Manger', 'UAUT', '1', '192.docx', '2017-07-08'),
(58, 'human rights ', 'ifm', '1', 'ooooooo.txt', '12/3/09');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `academic`
--
ALTER TABLE `academic`
 ADD PRIMARY KEY (`username`);

--
-- Indexes for table `apply`
--
ALTER TABLE `apply`
 ADD PRIMARY KEY (`appid`);

--
-- Indexes for table `call`
--
ALTER TABLE `call`
 ADD PRIMARY KEY (`appid`);

--
-- Indexes for table `profile`
--
ALTER TABLE `profile`
 ADD PRIMARY KEY (`username`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`username`);

--
-- Indexes for table `vacation`
--
ALTER TABLE `vacation`
 ADD PRIMARY KEY (`job_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `apply`
--
ALTER TABLE `apply`
MODIFY `appid` int(100) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=38;
--
-- AUTO_INCREMENT for table `vacation`
--
ALTER TABLE `vacation`
MODIFY `job_id` int(100) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=59;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
