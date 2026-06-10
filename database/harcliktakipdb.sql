-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 10 Haz 2026, 13:29:12
-- Sunucu sürümü: 10.4.32-MariaDB
-- PHP Sürümü: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `harcliktakipdb`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `expenses`
--

CREATE TABLE `expenses` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `total_money` double DEFAULT NULL,
  `spent_money` double DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Tablo döküm verisi `expenses`
--

INSERT INTO `expenses` (`id`, `user_id`, `total_money`, `spent_money`, `category`) VALUES
(1, 1, 5000, 0, NULL),
(4, 1, 300, 0, NULL),
(5, 1, 500, 0, NULL),
(6, 1, 0, 250, 'Yemek'),
(7, 1, 200, 0, NULL),
(8, 1, 0, 350, 'Ulasim'),
(9, 1, 0, 500, 'Yemek'),
(10, 1, 8000, 0, NULL),
(11, 1, 0, 900, 'Kiyafet'),
(12, 1, 500, 0, NULL),
(13, 1, 0, 250, 'Ulasim'),
(14, 1, 10000, 0, NULL),
(15, 1, 10000, 0, NULL),
(16, 3, 500, 0, NULL),
(17, 3, 0, 300, 'Telefon'),
(18, 5, 150000, 0, NULL),
(19, 5, 0, 20000, 'Elektronik'),
(20, 5, 0, 3000, 'Yemek'),
(21, 1, 1000, 0, NULL),
(22, 1, 0, 2000, 'Elektronik'),
(23, 3, 10000, 0, NULL),
(24, 3, 0, 2000, 'Sigara'),
(25, 6, 25000, 0, NULL),
(26, 6, 0, 6000, 'Yemek'),
(27, 6, 0, 3.5, 'Ulasim'),
(28, 6, 0, 35000, 'Ulasim'),
(29, 6, 0, 35000, 'Ulasim');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `fullname` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Tablo döküm verisi `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `fullname`, `email`) VALUES
(1, 'rabia', '123456', 'Rabia Tunçekin', 'rabia@gmail.com'),
(2, '', '', '', ''),
(3, 'bafrali', '654321', 'buse akkurt', 'buse@gmail.com'),
(4, '', '', '', ''),
(5, 'Zulal', '987654', 'Zulal Berrin Ercan', 'zulal@gmail.com'),
(6, 'Deniz', '454545', 'Deniz Yagmur', 'deniz@gmail.com');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `expenses`
--
ALTER TABLE `expenses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Tablo için indeksler `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `expenses`
--
ALTER TABLE `expenses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- Tablo için AUTO_INCREMENT değeri `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `expenses`
--
ALTER TABLE `expenses`
  ADD CONSTRAINT `expenses_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
