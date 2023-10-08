USE [master]
GO
/****** Object:  Database [electric_project]    Script Date: 04/10/2023 3:01:13 CH ******/
CREATE DATABASE [electric_project]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'electric_project', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MVL\MSSQL\DATA\electric_project.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'electric_project_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MVL\MSSQL\DATA\electric_project_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [electric_project] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [electric_project].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [electric_project] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [electric_project] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [electric_project] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [electric_project] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [electric_project] SET ARITHABORT OFF 
GO
ALTER DATABASE [electric_project] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [electric_project] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [electric_project] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [electric_project] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [electric_project] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [electric_project] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [electric_project] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [electric_project] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [electric_project] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [electric_project] SET  ENABLE_BROKER 
GO
ALTER DATABASE [electric_project] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [electric_project] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [electric_project] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [electric_project] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [electric_project] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [electric_project] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [electric_project] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [electric_project] SET RECOVERY FULL 
GO
ALTER DATABASE [electric_project] SET  MULTI_USER 
GO
ALTER DATABASE [electric_project] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [electric_project] SET DB_CHAINING OFF 
GO
ALTER DATABASE [electric_project] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [electric_project] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [electric_project] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [electric_project] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'electric_project', N'ON'
GO
ALTER DATABASE [electric_project] SET QUERY_STORE = ON
GO
ALTER DATABASE [electric_project] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [electric_project]
GO
/****** Object:  Table [dbo].[brands]    Script Date: 04/10/2023 3:01:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[brands](
	[brand_id] [int] IDENTITY(1,1) NOT NULL,
	[brand_name] [nvarchar](50) NOT NULL,
	[image] [nvarchar](50) NOT NULL,
	[active] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[brand_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[cart_details]    Script Date: 04/10/2023 3:01:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cart_details](
	[cartdetail_id] [int] IDENTITY(1,1) NOT NULL,
	[product_id] [int] NULL,
	[cart_id] [int] NULL,
	[quantity] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[cartdetail_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[carts]    Script Date: 04/10/2023 3:01:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[carts](
	[card_id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NOT NULL,
 CONSTRAINT [PK__carts__BDF201DD858CE39F] PRIMARY KEY CLUSTERED 
(
	[card_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[categorys]    Script Date: 04/10/2023 3:01:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[categorys](
	[category_id] [int] IDENTITY(1,1) NOT NULL,
	[category_name] [nvarchar](50) NOT NULL,
	[active] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[category_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[comments]    Script Date: 04/10/2023 3:01:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[comments](
	[comment_id] [int] IDENTITY(1,1) NOT NULL,
	[comment_date] [datetime] NOT NULL,
	[user_id] [int] NOT NULL,
	[product_id] [int] NOT NULL,
	[content] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK__comments__E795768701CC4FD4] PRIMARY KEY CLUSTERED 
(
	[comment_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ConfirmationCode]    Script Date: 04/10/2023 3:01:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ConfirmationCode](
	[user_id] [int] NOT NULL,
	[OTPCode] [nvarchar](100) NULL,
	[IsConfirmed] [bit] NULL,
	[OTPCreationDate] [datetime] NULL,
	[OTPExpirationDate] [datetime] NULL,
 CONSTRAINT [PK_ConfirmationCode] PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[news]    Script Date: 04/10/2023 3:01:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[news](
	[new_id] [int] IDENTITY(1,1) NOT NULL,
	[news_description] [nvarchar](max) NOT NULL,
	[image] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[new_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order_details]    Script Date: 04/10/2023 3:01:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_details](
	[order_detailId] [int] IDENTITY(1,1) NOT NULL,
	[order_id] [int] NOT NULL,
	[product_id] [int] NOT NULL,
	[quantity] [int] NOT NULL,
	[price] [float] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[order_detailId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orders]    Script Date: 04/10/2023 3:01:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orders](
	[order_id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[phone] [varchar](11) NOT NULL,
	[address] [nvarchar](max) NOT NULL,
	[order_date] [datetime] NOT NULL,
	[total_amount] [float] NOT NULL,
	[statushd] [tinyint] NOT NULL,
	[statustt] [bit] NOT NULL,
	[note] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK__orders__465962294BCAB6EA] PRIMARY KEY CLUSTERED 
(
	[order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[payment]    Script Date: 04/10/2023 3:01:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[payment](
	[payment_id] [int] IDENTITY(1,1) NOT NULL,
	[order_id] [int] NOT NULL,
	[payment_date] [datetime] NOT NULL,
	[payment_method] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[payment_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product_reviews]    Script Date: 04/10/2023 3:01:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product_reviews](
	[review_id] [int] IDENTITY(1,1) NOT NULL,
	[product_id] [int] NOT NULL,
	[user_id] [int] NOT NULL,
	[rating] [int] NOT NULL,
	[review_data] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK__product___60883D9047BF8A8D] PRIMARY KEY CLUSTERED 
(
	[review_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[products]    Script Date: 04/10/2023 3:01:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[products](
	[product_id] [int] IDENTITY(1,1) NOT NULL,
	[category_id] [int] NULL,
	[brand_id] [int] NULL,
	[product_name] [nvarchar](50) NULL,
	[price] [float] NULL,
	[create_date] [datetime] NULL,
	[description] [nvarchar](max) NULL,
	[image] [nvarchar](50) NULL,
	[quantity] [int] NULL,
	[active] [bit] NULL,
	[supplier_id] [int] NULL,
	[configuration] [nvarchar](max) NULL,
 CONSTRAINT [PK__products__47027DF54A9052AA] PRIMARY KEY CLUSTERED 
(
	[product_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Replys]    Script Date: 04/10/2023 3:01:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Replys](
	[reply_id] [int] IDENTITY(1,1) NOT NULL,
	[reply_content] [nvarchar](max) NULL,
	[responder_id] [int] NULL,
	[receiver_id] [int] NULL,
	[reply_date] [datetime] NULL,
	[comment_id] [int] NULL,
 CONSTRAINT [PK_Replys] PRIMARY KEY CLUSTERED 
(
	[reply_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[shippings]    Script Date: 04/10/2023 3:01:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[shippings](
	[transport_id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NOT NULL,
	[reciplient_name] [nvarchar](50) NOT NULL,
	[state] [bit] NOT NULL,
	[district] [nvarchar](50) NOT NULL,
	[address] [nvarchar](50) NOT NULL,
	[note] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK__shipping__A17E3277D85233DE] PRIMARY KEY CLUSTERED 
(
	[transport_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[suppliers]    Script Date: 04/10/2023 3:01:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[suppliers](
	[supplier_id] [int] NOT NULL,
	[supplier_name] [nvarchar](50) NOT NULL,
	[address] [nvarchar](100) NOT NULL,
	[phone] [varchar](10) NOT NULL,
	[email] [varchar](50) NOT NULL,
	[website] [varchar](50) NULL,
	[describe] [nvarchar](200) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[supplier_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 04/10/2023 3:01:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[user_id] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](50) NULL,
	[Password] [nvarchar](100) NULL,
	[Email] [nvarchar](100) NULL,
	[FullName] [nvarchar](100) NULL,
	[DateOfBirth] [date] NULL,
	[Gender] [bit] NULL,
	[Address] [nvarchar](200) NULL,
	[PhoneNumber] [nvarchar](20) NULL,
	[LoginPermission] [bit] NULL,
	[RegistrationDate] [datetime] NULL,
	[LockStatus] [bit] NULL,
	[Role] [nvarchar](10) NULL,
 CONSTRAINT [PK__Users__B9BE370FD688AA5F] PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[brands] ON 

INSERT [dbo].[brands] ([brand_id], [brand_name], [image], [active]) VALUES (1, N'JBL', N'banner_mini.png', 1)
INSERT [dbo].[brands] ([brand_id], [brand_name], [image], [active]) VALUES (2, N'Kangaroo', N'banner_mini1.png', 1)
INSERT [dbo].[brands] ([brand_id], [brand_name], [image], [active]) VALUES (3, N'Karofi', N'banner_mini2.png', 1)
INSERT [dbo].[brands] ([brand_id], [brand_name], [image], [active]) VALUES (4, N'LG', N'banner_mini3.png', 1)
INSERT [dbo].[brands] ([brand_id], [brand_name], [image], [active]) VALUES (5, N'Panasonic', N'banner_mini4.png', 1)
INSERT [dbo].[brands] ([brand_id], [brand_name], [image], [active]) VALUES (6, N'TCL', N'banner_mini7.png', 1)
INSERT [dbo].[brands] ([brand_id], [brand_name], [image], [active]) VALUES (7, N'Soni', N'banner_mini6.png', 1)
INSERT [dbo].[brands] ([brand_id], [brand_name], [image], [active]) VALUES (8, N'HP', N'dd9d8691-.jpg', 0)
INSERT [dbo].[brands] ([brand_id], [brand_name], [image], [active]) VALUES (1008, N'Dell', N'c0291977-.jpg', 0)
INSERT [dbo].[brands] ([brand_id], [brand_name], [image], [active]) VALUES (1009, N'Dell1', N'13d7ad8f-.jpg', 0)
SET IDENTITY_INSERT [dbo].[brands] OFF
GO
SET IDENTITY_INSERT [dbo].[categorys] ON 

INSERT [dbo].[categorys] ([category_id], [category_name], [active]) VALUES (1, N'Máy lạnh', 1)
INSERT [dbo].[categorys] ([category_id], [category_name], [active]) VALUES (2, N'Tủ lạnh', 1)
INSERT [dbo].[categorys] ([category_id], [category_name], [active]) VALUES (3, N'Tivi', 1)
INSERT [dbo].[categorys] ([category_id], [category_name], [active]) VALUES (4, N'Loa', 1)
INSERT [dbo].[categorys] ([category_id], [category_name], [active]) VALUES (15, N'Máy nước nóng', 0)
INSERT [dbo].[categorys] ([category_id], [category_name], [active]) VALUES (21, N'Máy giặc', 1)
SET IDENTITY_INSERT [dbo].[categorys] OFF
GO
SET IDENTITY_INSERT [dbo].[order_details] ON 

INSERT [dbo].[order_details] ([order_detailId], [order_id], [product_id], [quantity], [price]) VALUES (1, 1, 6, 2, 1500000)
INSERT [dbo].[order_details] ([order_detailId], [order_id], [product_id], [quantity], [price]) VALUES (2, 1, 8, 1, 15900000)
INSERT [dbo].[order_details] ([order_detailId], [order_id], [product_id], [quantity], [price]) VALUES (3, 1, 9, 1, 6490000)
INSERT [dbo].[order_details] ([order_detailId], [order_id], [product_id], [quantity], [price]) VALUES (4, 1, 7, 1, 2000000)
INSERT [dbo].[order_details] ([order_detailId], [order_id], [product_id], [quantity], [price]) VALUES (5, 2, 10, 1, 10590000)
INSERT [dbo].[order_details] ([order_detailId], [order_id], [product_id], [quantity], [price]) VALUES (6, 3, 6, 1, 1500000)
INSERT [dbo].[order_details] ([order_detailId], [order_id], [product_id], [quantity], [price]) VALUES (1005, 1003, 6, 1, 1500000)
INSERT [dbo].[order_details] ([order_detailId], [order_id], [product_id], [quantity], [price]) VALUES (1006, 1003, 7, 4, 2000000)
SET IDENTITY_INSERT [dbo].[order_details] OFF
GO
SET IDENTITY_INSERT [dbo].[orders] ON 

INSERT [dbo].[orders] ([order_id], [user_id], [name], [phone], [address], [order_date], [total_amount], [statushd], [statustt], [note]) VALUES (1, 2006, N'luak mai', N'0702807933', N'123,Xã Phú Phụng,Huyện Chợ Lách,Tỉnh Bến Tre', CAST(N'2023-09-27T00:00:00.000' AS DateTime), 27390000, 1, 0, N'123')
INSERT [dbo].[orders] ([order_id], [user_id], [name], [phone], [address], [order_date], [total_amount], [statushd], [statustt], [note]) VALUES (2, 2, N'admin', N'0989878675', N'55,Xã Đồng Phúc,Huyện Ba Bể,Tỉnh Bắc Kạn', CAST(N'2023-09-28T00:00:00.000' AS DateTime), 10590000, 1, 1, N'giao sớm nha')
INSERT [dbo].[orders] ([order_id], [user_id], [name], [phone], [address], [order_date], [total_amount], [statushd], [statustt], [note]) VALUES (3, 1, N'user', N'0987890987', N'776,Xã La Hiên,Huyện Võ Nhai,Tỉnh Thái Nguyên', CAST(N'2023-09-28T00:00:00.000' AS DateTime), 1500000, 1, 1, N'giao nè')
INSERT [dbo].[orders] ([order_id], [user_id], [name], [phone], [address], [order_date], [total_amount], [statushd], [statustt], [note]) VALUES (1003, 4008, N'Vu Luan Mai', N'0707900002', N'12,Xã Sen Thượng,Huyện Mường Nhé,Tỉnh Điện Biên', CAST(N'2023-10-02T00:00:00.000' AS DateTime), 9500000, 1, 1, N'123')
SET IDENTITY_INSERT [dbo].[orders] OFF
GO
SET IDENTITY_INSERT [dbo].[products] ON 

INSERT [dbo].[products] ([product_id], [category_id], [brand_id], [product_name], [price], [create_date], [description], [image], [quantity], [active], [supplier_id], [configuration]) VALUES (6, 3, 4, N'Google Tivi QLED Toshiba 4K 50 inch 50M550LP', 1500000, CAST(N'2023-09-22T00:00:00.000' AS DateTime), N'<h3>Tổng quan thiết kế</h3>

<p>&ndash; Toshiba 50M550LP thiết kế tinh giản, thanh mảnh, sang đẹp,&nbsp;<strong>khung h&igrave;nh tr&agrave;n viền ấn tượng</strong>&nbsp;mang đến trải nghiệm xem đ&atilde; mắt, sử dụng h&agrave;i h&ograve;a cho c&aacute;c kh&ocirc;ng gian sống hiện đại.</p>

<p>&ndash; M&agrave;n h&igrave;nh k&iacute;ch thước&nbsp;<strong>50 inch</strong>&nbsp;ph&ugrave; hợp cho c&aacute;c ph&ograve;ng kh&aacute;ch, ph&ograve;ng ngủ vừa v&agrave; nhỏ,&hellip;</p>

<p>&ndash;&nbsp;<strong>Ch&acirc;n đế chữ T &uacute;p ngược được l&agrave;m từ kim loại&nbsp;</strong>chắc chắn, n&acirc;ng đỡ tốt tivi tr&ecirc;n b&agrave;n hay kệ tủ, hoặc bạn c&oacute; thể th&aacute;o rời ch&acirc;n đế để bố tr&iacute; tivi treo tường gi&uacute;p tiết kiệm th&ecirc;m kh&ocirc;ng gian.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/297326/google-tivi-qled-toshiba-4k-50-inch-50m550lp-01.jpg"><img alt="Tinh giản, sang trọng - Google Tivi QLED Toshiba 4K 50 inch 50M550LP" src="https://cdn.tgdd.vn/Products/Images/1942/297326/google-tivi-qled-toshiba-4k-50-inch-50m550lp-01.jpg" /></a></p>

<p><em>* H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<h3>C&ocirc;ng nghệ h&igrave;nh ảnh</h3>

<p>&ndash; Google TV cho h&igrave;nh ảnh hiển thị sắc n&eacute;t đầy cuốn h&uacute;t với&nbsp;<strong>độ ph&acirc;n giải&nbsp;4K</strong>.</p>

<p>&ndash;&nbsp;<strong>M&agrave;n h&igrave;nh QLED</strong>&nbsp;rực rỡ, sống động qua h&agrave;ng tỷ sắc th&aacute;i m&agrave;u sắc từ&nbsp;<strong>c&ocirc;ng nghệ chấm lượng tử&nbsp;Quantum Dot</strong>,&nbsp;<strong>c&ocirc;ng nghệ Color Re-Master Pro&nbsp;</strong>kh&ocirc;i phục m&agrave;u gốc của h&igrave;nh ảnh một c&aacute;ch tự nhi&ecirc;n,&nbsp;<strong>Ultra Essential PQ</strong>&nbsp;ph&acirc;n t&iacute;ch từng pixel của từng cảnh v&agrave; điều chỉnh c&aacute;c th&ocirc;ng số&nbsp;cho từng chi tiết, nhờ đ&oacute; người d&ugrave;ng trải nghiệm sự ch&acirc;n thực ho&agrave;n hảo hơn cho từng nội dung xem tr&ecirc;n m&agrave;n h&igrave;nh tivi.</p>

<p>&ndash;&nbsp;Nội dung được tối ưu qua&nbsp;<strong>bộ xử l&yacute; Regza Engine 4K Pro</strong>,&nbsp;<strong>c&ocirc;ng nghệ&nbsp;AI 4K Upscaling</strong>&nbsp;n&acirc;ng cấp nội dung đầu v&agrave;o l&ecirc;n chuẩn gần 4K để người xem lu&ocirc;n được thưởng thức khung h&igrave;nh chất lượng nhất.</p>

<p>&ndash;&nbsp;Dải tương phản&nbsp;<strong>HDR10+</strong>,&nbsp;<strong>HDR Restoration</strong>&nbsp;c&ugrave;ng c&ocirc;ng nghệ&nbsp;<strong>Full Array Local Dimming</strong>&nbsp;gia tăng chất lượng tương phản, hiển thị r&otilde; n&eacute;t v&agrave; c&oacute; chiều s&acirc;u tr&ecirc;n từng chi tiết khung h&igrave;nh, ph&acirc;n t&aacute;ch r&otilde; r&agrave;ng hơn giữa v&ugrave;ng s&aacute;ng v&agrave; v&ugrave;ng tối, tạo kh&ocirc;ng gian giải tr&iacute; đắm ch&igrave;m cho người thưởng thức.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/297326/google-tivi-qled-toshiba-4k-50-inch-50m550lp-02.jpg"><img alt="Regza Engine 4K Pro - Google Tivi QLED Toshiba 4K 50 inch 50M550LP" src="https://cdn.tgdd.vn/Products/Images/1942/297326/google-tivi-qled-toshiba-4k-50-inch-50m550lp-02.jpg" /></a></p>

<p><em>* H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<p>&ndash; Khung h&igrave;nh chuyển động th&ecirc;m mượt m&agrave; qua c&ocirc;ng nghệ tăng cường chuyển động v&agrave; giảm độ trễ&nbsp;<strong>Ultimate motion (MEMC), Auto Low-Latency</strong>&nbsp;để tối ưu c&aacute;c trải nghiệm giải tr&iacute; thể thao, phim ảnh hay chiến game tr&ecirc;n m&agrave;n h&igrave;nh&nbsp;<a href="https://www.dienmayxanh.com/tivi" target="_blank">tivi</a>.</p>

<p>&ndash; Ngo&agrave;i ra,&nbsp;c&ocirc;ng nghệ giảm &aacute;nh s&aacute;ng xanh&nbsp;<strong>iCare Blue&nbsp;</strong>tr&ecirc;n&nbsp;tivi QLED&nbsp;n&agrave;y gi&uacute;p bảo vệ mắt của bạn khi ngồi l&acirc;u trước m&agrave;n h&igrave;nh nhờ khả năng lọc bỏ bớt &aacute;nh s&aacute;ng g&acirc;y hại ph&aacute;t ra từ m&agrave;n h&igrave;nh, tăng sự thoải m&aacute;i cho người xem li&ecirc;n tục nhiều giờ liền.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/297326/google-tivi-qled-toshiba-4k-50-inch-50m550lp-03.jpg"><img alt="MEMC - Google Tivi QLED Toshiba 4K 50 inch 50M550LP" src="https://cdn.tgdd.vn/Products/Images/1942/297326/google-tivi-qled-toshiba-4k-50-inch-50m550lp-03.jpg" /></a></p>

<p><em>* H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<h3>C&ocirc;ng nghệ &acirc;m thanh</h3>

<p>&ndash;&nbsp;Tivi trang bị hệ thống&nbsp;<strong>3 loa</strong>&nbsp;c&oacute; tổng c&ocirc;ng suất l&ecirc;n đến&nbsp;<strong>49W&nbsp;</strong>tạo &acirc;m thanh mạnh mẽ, vang dội.</p>

<p>&ndash; Hiệu ứng &acirc;m v&ograve;m qua c&ocirc;ng nghệ&nbsp;<strong>Dolby Atmos,&nbsp;Dolby Audio</strong>&nbsp;khuấy động kh&ocirc;ng gian giải tr&iacute; của bạn, c&ugrave;ng với c&ocirc;ng nghệ<strong>&nbsp;Regza Power Audio Pro</strong>&nbsp;được t&iacute;ch hợp gia tăng hiệu ứng &acirc;m v&ograve;m&nbsp;để bạn tận hưởng kh&ocirc;ng gian như rạp chiếu phim ngay tại nh&agrave;.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/297326/google-tivi-qled-toshiba-4k-50-inch-50m550lp-04.jpg"><img alt="Dolby Atmos - Google Tivi QLED Toshiba 4K 50 inch 50M550LP" src="https://cdn.tgdd.vn/Products/Images/1942/297326/google-tivi-qled-toshiba-4k-50-inch-50m550lp-04.jpg" /></a></p>

<p><em>* H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<h3>Hệ điều h&agrave;nh</h3>

<p>&ndash;&nbsp;Tivi Toshiba&nbsp;sử dụng&nbsp;<strong>hệ điều h&agrave;nh Google TV&nbsp;</strong>giao diện&nbsp;th&acirc;n thiện. Kho ứng dụng đa dạng với c&aacute;c ứng dụng c&oacute; sẵn như: Live TV, Netflix, Prime Video, Tr&igrave;nh duyệt web, TV 360, VieON, YouTube,&hellip; v&agrave; c&aacute;c ứng dụng tải th&ecirc;m kh&aacute;c đảm bảo đ&aacute;p ứng nhu cầu giải tr&iacute; cho mọi th&agrave;nh vi&ecirc;n trong gia đ&igrave;nh.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/297326/google-tivi-qled-toshiba-4k-50-inch-50m550lp-05.jpg"><img alt="Google TV - Google Tivi QLED Toshiba 4K 50 inch 50M550LP" src="https://cdn.tgdd.vn/Products/Images/1942/297326/google-tivi-qled-toshiba-4k-50-inch-50m550lp-05.jpg" /></a></p>

<p><em>* H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<h3>Tiện &iacute;ch</h3>

<p>&ndash;&nbsp;Google TV&nbsp;c&oacute;&nbsp;micro t&iacute;ch hợp tr&ecirc;n tivi cho ph&eacute;p&nbsp;<strong>điều khiển giọng n&oacute;i rảnh tay</strong>&nbsp;với sự hỗ trợ của&nbsp;trợ l&yacute; ảo Google Assistant (c&oacute; tiếng Việt).</p>

<p>&ndash;&nbsp;<strong>Remote t&iacute;ch hợp micro</strong>&nbsp;đi k&egrave;m&nbsp;dễ d&agrave;ng thực hiện c&aacute;c lệnh t&igrave;m kiếm nhanh bằng khẩu lệnh m&agrave; kh&ocirc;ng cần qua c&aacute;c thao t&aacute;c rườm r&agrave;, tiện lợi hơn, nhanh gọn hơn.</p>

<p>&ndash; Ngo&agrave;i ra, bạn c&oacute; thể truyền ph&aacute;t nội dung xem tr&ecirc;n điện thoại, m&aacute;y t&iacute;nh bảng của m&igrave;nh l&ecirc;n m&agrave;n h&igrave;nh tivi để c&oacute; được khung h&igrave;nh lớn hơn th&ocirc;ng qua&nbsp;<strong>t&iacute;nh năng&nbsp;Chromecast,&nbsp;AirPlay 2,&nbsp;</strong>DLNA, Miracast, Content Sharing.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/297326/google-tivi-qled-toshiba-4k-50-inch-50m550lp-06.jpg"><img alt="Chiếu màn hình TV - Google Tivi QLED Toshiba 4K 50 inch 50M550LP" src="https://cdn.tgdd.vn/Products/Images/1942/297326/google-tivi-qled-toshiba-4k-50-inch-50m550lp-06.jpg" /></a></p>

<p><em>* H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<p><em>T&oacute;m lại, mẫu Google Tivi QLED Toshiba 4K 50 inch 50M550LP tinh giản, hiện đại m&agrave; cực kỳ chất lượng n&agrave;y sẽ l&agrave; một lựa chọn tốt cho nhu cầu giải tr&iacute; tại gia đ&igrave;nh, thưởng thức c&aacute;c nội dung y&ecirc;u th&iacute;ch tr&ecirc;n m&agrave;n h&igrave;nh 4K sắc n&eacute;t, m&agrave;u sắc sống động nhờ Quantum Dot, tương phản tốt HDR10+, &acirc;m thanh v&ograve;m Dolby Atmos, c&ugrave;ng c&aacute;c tiện &iacute;ch thiết thực kh&aacute;c, để d&ugrave; chỉ ở nh&agrave;, bạn vẫn dễ d&agrave;ng tận hưởng kh&ocirc;ng kh&iacute; rạp chiếu lớn đầy sức h&uacute;t v&agrave; hứng khởi.</em></p>
', N'tivi1.png', 1, 1, 1, N'<table class="table" style="width:1099px">
	<tbody>
		<tr>
			<td>Loại tivi:</td>
			<td>Google Tivi50 inch4K</td>
		</tr>
		<tr>
			<td>Hệ điều h&agrave;nh</td>
			<td>Tizen&trade;</td>
		</tr>
		<tr>
			<td>Ứng dụng phổ biến</td>
			<td>Clip TVFPT PlayGalaxy Play (Fim+)MP3 ZingMyTVNetflixPOPS KidsSpotifyTr&igrave;nh duyệt webVieONYouTube</td>
		</tr>
		<tr>
			<td>C&ocirc;ng nghệ h&igrave;nh ảnh</td>
			<td>Auto ModeChuyển động mượt Motionflow XR 200Dolby VisionHDR10HLGKiểm so&aacute;t đ&egrave;n nền Direct LED Frame DimmingN&acirc;ng cấp h&igrave;nh ảnh 4K X-Reality PROTăng cường m&agrave;u sắc Triluminos Pro</td>
		</tr>
		<tr>
			<td>Chiếu h&igrave;nh từ điện thoại l&ecirc;n TV</td>
			<td>AirPlay 2Screen Mirroring</td>
		</tr>
		<tr>
			<td>K&iacute;ch thước:</td>
			<td>Ngang 111.9 cm &ndash; Cao 71.6 cm &ndash; D&agrave;y 28.8 cm</td>
		</tr>
		<tr>
			<td>Năm ra mắt</td>
			<td>2022</td>
		</tr>
		<tr>
			<td>H&atilde;ng</td>
			<td>Sony</td>
		</tr>
	</tbody>
</table>
')
INSERT [dbo].[products] ([product_id], [category_id], [brand_id], [product_name], [price], [create_date], [description], [image], [quantity], [active], [supplier_id], [configuration]) VALUES (7, 2, 2, N'Smart Tivi LG 4K 50 inch 50UQ7550PSF', 2000000, CAST(N'2023-09-22T00:00:00.000' AS DateTime), N'<h3>Tổng quan thiết kế</h3>

<p>&ndash; Tivi sở hữu thiết kế thanh mảnh c&ugrave;ng phần&nbsp;<strong>viền mỏng</strong>&nbsp;ấn tượng, gi&uacute;p mở rộng khung h&igrave;nh tối đa, đem đến cảm gi&aacute;c đ&atilde; mắt cho người xem khi thưởng thức nội dung tr&ecirc;n m&agrave;n h&igrave;nh.</p>

<p>&ndash; Ch&acirc;n đế được l&agrave;m từ nhựa cao cấp với tạo h&igrave;nh 2 ch&acirc;n đỡ độc đ&aacute;o, chắc chắn. Biến kh&ocirc;ng gian trưng b&agrave;y trở n&ecirc;n sang trọng, h&uacute;t mắt hơn bao giờ hết.</p>

<p>&ndash; K&iacute;ch thước 50 inch&nbsp;th&iacute;ch hợp lắp đặt cho c&aacute;c kh&ocirc;ng gian như ph&ograve;ng kh&aacute;ch, ph&ograve;ng ngủ, hay c&aacute;c khu vực ph&ograve;ng họp nhỏ,&hellip;</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-240322-033633.jpg"><img alt="Android Tivi Sony 4K 50 inch KD-50X80K - Thiết kế" src="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-240322-033633.jpg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa sản phẩm</em></p>

<h3>C&ocirc;ng nghệ h&igrave;nh ảnh</h3>

<p>&ndash; Thưởng thức h&igrave;nh ảnh sắc n&eacute;t, r&otilde; r&agrave;ng hơn gấp 4 lần tivi Full HD th&ocirc;ng thường nhờ&nbsp;<strong>độ ph&acirc;n giải 4K của&nbsp;</strong>Android tivi Sony.</p>

<p>&ndash;&nbsp;<strong>Bộ xử l&yacute; X1 4K HDR</strong>&nbsp;giảm mờ v&agrave; tăng độ chi tiết cho h&igrave;nh ảnh, mang đến những ph&uacute;t gi&acirc;y giải tr&iacute; m&atilde;n nh&atilde;n, rực rỡ sắc m&agrave;u với độ tương phản sắc n&eacute;t.</p>

<p>&ndash;&nbsp;<strong>C&ocirc;ng nghệ Dolby Vision</strong>&nbsp;kết hợp c&ugrave;ng&nbsp;<strong>Triluminos Pro&nbsp;</strong>cho trải nghiệm khung cảnh m&agrave;u sắc rực rỡ, trung thực chuẩn điện ảnh với độ s&acirc;u m&agrave;u ấn tượng.</p>

<p>&ndash;&nbsp;<strong>C&ocirc;ng nghệ HDR10&nbsp;</strong>thổi hồn v&agrave;o từng chi tiết trong khung cảnh<strong>,&nbsp;</strong>tự động c&acirc;n chỉnh độ tương phản ph&ugrave; hợp với từng khung h&igrave;nh<strong>&nbsp;</strong>cho độ s&acirc;u hiển thị r&otilde; n&eacute;t hơn.</p>

<p>&ndash; N&acirc;ng cấp chất lượng hiển thị h&igrave;nh ảnh với&nbsp;<strong>c&ocirc;ng nghệ&nbsp;4K X-Reality PRO&nbsp;</strong>đem đến những thước phim mượt m&agrave;, m&agrave;u sắc rực rỡ, gần với tự nhi&ecirc;n nhất.</p>

<p>Mời bạn xem th&ecirc;m:&nbsp;Những độ ph&acirc;n giải m&agrave;n h&igrave;nh phổ biến hiện nay tr&ecirc;n tivi</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-280622-095704.gif"><img alt="Android Tivi Sony 4K 50 inch KD-50X80K - Công nghệ hình ảnh" src="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-280622-095704.gif" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa sản phẩm</em></p>

<h3>C&ocirc;ng nghệ &acirc;m thanh</h3>

<p>&ndash; Tổng c&ocirc;ng suất loa&nbsp;<strong>20W.</strong></p>

<p>&ndash;&nbsp;<strong>Loa&nbsp;X-Balanced</strong>&nbsp;hỗ trợ l&agrave;m tăng chất lượng &acirc;m thanh, đem đến kh&ocirc;ng gian giải tr&iacute; sống động, &acirc;m thanh r&otilde; n&eacute;t, phong ph&uacute;.</p>

<p>&ndash;&nbsp;&Acirc;m thanh v&ograve;m sống động được t&aacute;i hiện hiện qua&nbsp;<strong>c&ocirc;ng nghệ&nbsp;Dolby Atmos</strong>&nbsp;v&agrave; DTS đem lại hiệu ứng &acirc;m thanh b&ugrave;ng nổ,&nbsp;cho người xem h&ograve;a nhập nội dung giải tr&iacute;, cảm nhận r&otilde; r&agrave;ng hơn những g&igrave; đang diễn ra tr&ecirc;n m&agrave;n h&igrave;nh tivi.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-220722-105504.jpeg"><img alt="Android Tivi Sony 4K 50 inch KD-50X80K - Âm thanh vòm Dobly Atmos" src="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-220722-105504.jpeg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa sản phẩm</em></p>

<h3>Hệ điều h&agrave;nh</h3>

<p><strong>Hệ điều h&agrave;nh&nbsp;Google TV&nbsp;</strong>với&nbsp;giao diện th&acirc;n thiện, dễ sử dụng. Kết hợp c&ugrave;ng kho ứng dụng phong ph&uacute;: FPT Play, Galaxy Play (Fim+), Google Play, Netflix, VieON, YouTube,&hellip; đ&aacute;p ứng đa dạng c&aacute;c nhu cầu giải tr&iacute; cho gia đ&igrave;nh.</p>

<p>Xem th&ecirc;m:&nbsp;C&aacute;ch xem phim bằng tr&igrave;nh duyệt web tr&ecirc;n tivi</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-220722-105548.jpeg"><img alt="Android Tivi Sony 4K 50 inch KD-50X80K - Hệ điều hành Google TV" src="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-220722-105548.jpeg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa sản phẩm</em></p>

<h3>Tiện &iacute;ch</h3>

<p>&ndash;&nbsp;<strong>T&igrave;m kiếm&nbsp;bằng giọng n&oacute;i&nbsp;</strong>với&nbsp;trợ l&yacute; ảo<strong>&nbsp;Google Assistant&nbsp;</strong>(c&oacute; hỗ trợ tiếng Việt).</p>

<p>&ndash;&nbsp;<strong>Remote t&iacute;ch hợp t&iacute;nh năng micro&nbsp;</strong>t&igrave;m kiếm giọng n&oacute;i 3 miền<strong>&nbsp;</strong>hay&nbsp;<strong>micro&nbsp;tr&ecirc;n TV</strong>&nbsp;điều khiển giọng n&oacute;i rảnh tay (n&oacute;i trực tiếp v&agrave;o tivi).</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-110822-093908.jpg"><img alt="Android Tivi Sony 4K 50 inch KD-50X80K - Tìm kiếm bằng giọng nói" src="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-110822-093908.jpg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa sản phẩm</em></p>

<p>&ndash; Dễ d&agrave;ng&nbsp;<strong>tr&igrave;nh chiếu c&aacute;c chương tr&igrave;nh y&ecirc;u th&iacute;ch từ m&agrave;n h&igrave;nh điện thoại l&ecirc;n&nbsp;<a href="https://www.dienmayxanh.com/tivi" target="_blank">tivi</a></strong>&nbsp;th&ocirc;ng qua ứng dụng&nbsp;<strong>AirPlay 2&nbsp;</strong>(iPhone)&nbsp;v&agrave;<strong>&nbsp;Chromecast.</strong></p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-220722-105659.jpeg"><img alt="Android Tivi Sony 4K 50 inch KD-50X80K  - Tiện ích thông minh" src="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-220722-105659.jpeg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa sản phẩm</em></p>

<p>&ndash; Duy tr&igrave; li&ecirc;n lạc với người th&acirc;n, tổ chức c&aacute;c buổi họp trực tuyến để l&agrave;m việc, ph&aacute;t triển kinh doanh hiệu quả c&ugrave;ng&nbsp;<strong>Bravia CAM</strong>&nbsp;(mua th&ecirc;m camera), ứng dụng cho ph&eacute;p kết nối v&agrave; tr&ograve; chuyện video qua tivi tiện lợi.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/274757/android-sony-4k-75-inch-kd-75x80k-230922-112018.jpg"><img alt="Google Tivi Sony 4K 55 inch KD-50X80K -  Bravia CAM" src="https://cdn.tgdd.vn/Products/Images/1942/274757/android-sony-4k-75-inch-kd-75x80k-230922-112018.jpg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa cho sản phẩm</em></p>

<p><em>Nh&igrave;n chung,&nbsp;Google Tivi Sony 4K 50 inch KD-50X80K&nbsp;mang&nbsp;thiết kế m&agrave;n h&igrave;nh mỏng sang trọng, ấn tượng với bộ xử l&yacute; h&igrave;nh ảnh X1 4K HDR v&agrave; c&ocirc;ng nghệ HDR10 cho h&igrave;nh ảnh chi tiết, rực rỡ. C&ocirc;ng nghệ &acirc;m thanh v&ograve;m Dolby Atmos cho &acirc;m thanh b&ugrave;ng nổ, gi&uacute;p người xem đắm ch&igrave;m, h&ograve;a m&igrave;nh v&agrave;o từng ph&uacute;t gi&acirc;y giải tr&iacute; tinh xảo.&nbsp;</em></p>
', N'tivi.png', 123, 1, 1, N'<table class="table" style="width:1099px">
	<tbody>
		<tr>
			<td>Loại tivi:</td>
			<td>Google Tivi50 inch4K</td>
		</tr>
		<tr>
			<td>Hệ điều h&agrave;nh</td>
			<td>Tizen&trade;</td>
		</tr>
		<tr>
			<td>Ứng dụng phổ biến</td>
			<td>Clip TVFPT PlayGalaxy Play (Fim+)MP3 ZingMyTVNetflixPOPS KidsSpotifyTr&igrave;nh duyệt webVieONYouTube</td>
		</tr>
		<tr>
			<td>C&ocirc;ng nghệ h&igrave;nh ảnh</td>
			<td>Auto ModeChuyển động mượt Motionflow XR 200Dolby VisionHDR10HLGKiểm so&aacute;t đ&egrave;n nền Direct LED Frame DimmingN&acirc;ng cấp h&igrave;nh ảnh 4K X-Reality PROTăng cường m&agrave;u sắc Triluminos Pro</td>
		</tr>
		<tr>
			<td>Chiếu h&igrave;nh từ điện thoại l&ecirc;n TV</td>
			<td>AirPlay 2Screen Mirroring</td>
		</tr>
		<tr>
			<td>K&iacute;ch thước:</td>
			<td>Ngang 111.9 cm &ndash; Cao 71.6 cm &ndash; D&agrave;y 28.8 cm</td>
		</tr>
		<tr>
			<td>Năm ra mắt</td>
			<td>2022</td>
		</tr>
		<tr>
			<td>H&atilde;ng</td>
			<td>Sony</td>
		</tr>
	</tbody>
</table>
')
INSERT [dbo].[products] ([product_id], [category_id], [brand_id], [product_name], [price], [create_date], [description], [image], [quantity], [active], [supplier_id], [configuration]) VALUES (8, 2, 3, N'Tivi Sony 4K 50 inch KD-50X80K', 15900000, CAST(N'2023-09-23T00:00:00.000' AS DateTime), N'<h3>Tổng quan thiết kế</h3>

<p>&ndash; Tivi sở hữu thiết kế thanh mảnh c&ugrave;ng phần&nbsp;<strong>viền mỏng</strong>&nbsp;ấn tượng, gi&uacute;p mở rộng khung h&igrave;nh tối đa, đem đến cảm gi&aacute;c đ&atilde; mắt cho người xem khi thưởng thức nội dung tr&ecirc;n m&agrave;n h&igrave;nh.</p>

<p>&ndash; Ch&acirc;n đế được l&agrave;m từ nhựa cao cấp với tạo h&igrave;nh 2 ch&acirc;n đỡ độc đ&aacute;o, chắc chắn. Biến kh&ocirc;ng gian trưng b&agrave;y trở n&ecirc;n sang trọng, h&uacute;t mắt hơn bao giờ hết.</p>

<p>&ndash; K&iacute;ch thước 50 inch&nbsp;th&iacute;ch hợp lắp đặt cho c&aacute;c kh&ocirc;ng gian như ph&ograve;ng kh&aacute;ch, ph&ograve;ng ngủ, hay c&aacute;c khu vực ph&ograve;ng họp nhỏ,&hellip;</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-240322-033633.jpg"><img alt="Android Tivi Sony 4K 50 inch KD-50X80K - Thiết kế" src="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-240322-033633.jpg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa sản phẩm</em></p>

<h3>C&ocirc;ng nghệ h&igrave;nh ảnh</h3>

<p>&ndash; Thưởng thức h&igrave;nh ảnh sắc n&eacute;t, r&otilde; r&agrave;ng hơn gấp 4 lần tivi Full HD th&ocirc;ng thường nhờ&nbsp;<strong>độ ph&acirc;n giải 4K của&nbsp;</strong>Android tivi Sony.</p>

<p>&ndash;&nbsp;<strong>Bộ xử l&yacute; X1 4K HDR</strong>&nbsp;giảm mờ v&agrave; tăng độ chi tiết cho h&igrave;nh ảnh, mang đến những ph&uacute;t gi&acirc;y giải tr&iacute; m&atilde;n nh&atilde;n, rực rỡ sắc m&agrave;u với độ tương phản sắc n&eacute;t.</p>

<p>&ndash;&nbsp;<strong>C&ocirc;ng nghệ Dolby Vision</strong>&nbsp;kết hợp c&ugrave;ng&nbsp;<strong>Triluminos Pro&nbsp;</strong>cho trải nghiệm khung cảnh m&agrave;u sắc rực rỡ, trung thực chuẩn điện ảnh với độ s&acirc;u m&agrave;u ấn tượng.</p>

<p>&ndash;&nbsp;<strong>C&ocirc;ng nghệ HDR10&nbsp;</strong>thổi hồn v&agrave;o từng chi tiết trong khung cảnh<strong>,&nbsp;</strong>tự động c&acirc;n chỉnh độ tương phản ph&ugrave; hợp với từng khung h&igrave;nh<strong>&nbsp;</strong>cho độ s&acirc;u hiển thị r&otilde; n&eacute;t hơn.</p>

<p>&ndash; N&acirc;ng cấp chất lượng hiển thị h&igrave;nh ảnh với&nbsp;<strong>c&ocirc;ng nghệ&nbsp;4K X-Reality PRO&nbsp;</strong>đem đến những thước phim mượt m&agrave;, m&agrave;u sắc rực rỡ, gần với tự nhi&ecirc;n nhất.</p>

<p>Mời bạn xem th&ecirc;m:&nbsp;Những độ ph&acirc;n giải m&agrave;n h&igrave;nh phổ biến hiện nay tr&ecirc;n tivi</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-280622-095704.gif"><img alt="Android Tivi Sony 4K 50 inch KD-50X80K - Công nghệ hình ảnh" src="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-280622-095704.gif" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa sản phẩm</em></p>

<h3>C&ocirc;ng nghệ &acirc;m thanh</h3>

<p>&ndash; Tổng c&ocirc;ng suất loa&nbsp;<strong>20W.</strong></p>

<p>&ndash;&nbsp;<strong>Loa&nbsp;X-Balanced</strong>&nbsp;hỗ trợ l&agrave;m tăng chất lượng &acirc;m thanh, đem đến kh&ocirc;ng gian giải tr&iacute; sống động, &acirc;m thanh r&otilde; n&eacute;t, phong ph&uacute;.</p>

<p>&ndash;&nbsp;&Acirc;m thanh v&ograve;m sống động được t&aacute;i hiện hiện qua&nbsp;<strong>c&ocirc;ng nghệ&nbsp;Dolby Atmos</strong>&nbsp;v&agrave; DTS đem lại hiệu ứng &acirc;m thanh b&ugrave;ng nổ,&nbsp;cho người xem h&ograve;a nhập nội dung giải tr&iacute;, cảm nhận r&otilde; r&agrave;ng hơn những g&igrave; đang diễn ra tr&ecirc;n m&agrave;n h&igrave;nh tivi.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-220722-105504.jpeg"><img alt="Android Tivi Sony 4K 50 inch KD-50X80K - Âm thanh vòm Dobly Atmos" src="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-220722-105504.jpeg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa sản phẩm</em></p>

<h3>Hệ điều h&agrave;nh</h3>

<p><strong>Hệ điều h&agrave;nh&nbsp;Google TV&nbsp;</strong>với&nbsp;giao diện th&acirc;n thiện, dễ sử dụng. Kết hợp c&ugrave;ng kho ứng dụng phong ph&uacute;: FPT Play, Galaxy Play (Fim+), Google Play, Netflix, VieON, YouTube,&hellip; đ&aacute;p ứng đa dạng c&aacute;c nhu cầu giải tr&iacute; cho gia đ&igrave;nh.</p>

<p>Xem th&ecirc;m:&nbsp;C&aacute;ch xem phim bằng tr&igrave;nh duyệt web tr&ecirc;n tivi</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-220722-105548.jpeg"><img alt="Android Tivi Sony 4K 50 inch KD-50X80K - Hệ điều hành Google TV" src="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-220722-105548.jpeg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa sản phẩm</em></p>

<h3>Tiện &iacute;ch</h3>

<p>&ndash;&nbsp;<strong>T&igrave;m kiếm&nbsp;bằng giọng n&oacute;i&nbsp;</strong>với&nbsp;trợ l&yacute; ảo<strong>&nbsp;Google Assistant&nbsp;</strong>(c&oacute; hỗ trợ tiếng Việt).</p>

<p>&ndash;&nbsp;<strong>Remote t&iacute;ch hợp t&iacute;nh năng micro&nbsp;</strong>t&igrave;m kiếm giọng n&oacute;i 3 miền<strong>&nbsp;</strong>hay&nbsp;<strong>micro&nbsp;tr&ecirc;n TV</strong>&nbsp;điều khiển giọng n&oacute;i rảnh tay (n&oacute;i trực tiếp v&agrave;o tivi).</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-110822-093908.jpg"><img alt="Android Tivi Sony 4K 50 inch KD-50X80K - Tìm kiếm bằng giọng nói" src="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-110822-093908.jpg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa sản phẩm</em></p>

<p>&ndash; Dễ d&agrave;ng&nbsp;<strong>tr&igrave;nh chiếu c&aacute;c chương tr&igrave;nh y&ecirc;u th&iacute;ch từ m&agrave;n h&igrave;nh điện thoại l&ecirc;n&nbsp;<a href="https://www.dienmayxanh.com/tivi" target="_blank">tivi</a></strong>&nbsp;th&ocirc;ng qua ứng dụng&nbsp;<strong>AirPlay 2&nbsp;</strong>(iPhone)&nbsp;v&agrave;<strong>&nbsp;Chromecast.</strong></p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-220722-105659.jpeg"><img alt="Android Tivi Sony 4K 50 inch KD-50X80K  - Tiện ích thông minh" src="https://cdn.tgdd.vn/Products/Images/1942/274762/android-sony-4k-50-inch-kd-50x80k-220722-105659.jpeg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa sản phẩm</em></p>

<p>&ndash; Duy tr&igrave; li&ecirc;n lạc với người th&acirc;n, tổ chức c&aacute;c buổi họp trực tuyến để l&agrave;m việc, ph&aacute;t triển kinh doanh hiệu quả c&ugrave;ng&nbsp;<strong>Bravia CAM</strong>&nbsp;(mua th&ecirc;m camera), ứng dụng cho ph&eacute;p kết nối v&agrave; tr&ograve; chuyện video qua tivi tiện lợi.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/274757/android-sony-4k-75-inch-kd-75x80k-230922-112018.jpg"><img alt="Google Tivi Sony 4K 55 inch KD-50X80K -  Bravia CAM" src="https://cdn.tgdd.vn/Products/Images/1942/274757/android-sony-4k-75-inch-kd-75x80k-230922-112018.jpg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa cho sản phẩm</em></p>

<p><em>Nh&igrave;n chung,&nbsp;Google Tivi Sony 4K 50 inch KD-50X80K&nbsp;mang&nbsp;thiết kế m&agrave;n h&igrave;nh mỏng sang trọng, ấn tượng với bộ xử l&yacute; h&igrave;nh ảnh X1 4K HDR v&agrave; c&ocirc;ng nghệ HDR10 cho h&igrave;nh ảnh chi tiết, rực rỡ. C&ocirc;ng nghệ &acirc;m thanh v&ograve;m Dolby Atmos cho &acirc;m thanh b&ugrave;ng nổ, gi&uacute;p người xem đắm ch&igrave;m, h&ograve;a m&igrave;nh v&agrave;o từng ph&uacute;t gi&acirc;y giải tr&iacute; tinh xảo.&nbsp;</em></p>
', N'tivi2.png', 1, 1, 1, N'<h2>TH&Ocirc;NG SỐ KỸ THUẬT</h2>

<table style="width:1099px">
	<tbody>
		<tr>
			<td>Loại tivi:</td>
			<td>Google Tivi50 inch4K</td>
		</tr>
		<tr>
			<td>Hệ điều h&agrave;nh</td>
			<td>Tizen&trade;</td>
		</tr>
		<tr>
			<td>Ứng dụng phổ biến</td>
			<td>Clip TVFPT PlayGalaxy Play (Fim+)MP3 ZingMyTVNetflixPOPS KidsSpotifyTr&igrave;nh duyệt webVieONYouTube</td>
		</tr>
		<tr>
			<td>C&ocirc;ng nghệ h&igrave;nh ảnh</td>
			<td>Auto ModeChuyển động mượt Motionflow XR 200Dolby VisionHDR10HLGKiểm so&aacute;t đ&egrave;n nền Direct LED Frame DimmingN&acirc;ng cấp h&igrave;nh ảnh 4K X-Reality PROTăng cường m&agrave;u sắc Triluminos Pro</td>
		</tr>
		<tr>
			<td>Chiếu h&igrave;nh từ điện thoại l&ecirc;n TV</td>
			<td>AirPlay 2Screen Mirroring</td>
		</tr>
		<tr>
			<td>K&iacute;ch thước:</td>
			<td>Ngang 111.9 cm &ndash; Cao 71.6 cm &ndash; D&agrave;y 28.8 cm</td>
		</tr>
		<tr>
			<td>Năm ra mắt</td>
			<td>2022</td>
		</tr>
		<tr>
			<td>H&atilde;ng</td>
			<td>Sony</td>
		</tr>
	</tbody>
</table>
')
INSERT [dbo].[products] ([product_id], [category_id], [brand_id], [product_name], [price], [create_date], [description], [image], [quantity], [active], [supplier_id], [configuration]) VALUES (9, 3, 4, N'Tivi TCL 4K 43 inch 43P635', 6490000, CAST(N'2023-09-02T00:00:00.000' AS DateTime), N'<p><strong><em>Google Tivi TCL 4K 43 inch 43P635&nbsp;thiết kế mỏng đẹp, h&igrave;nh ảnh 4K hiển thị sắc n&eacute;t, c&ocirc;ng nghệ&nbsp;HDR10&nbsp;cho khung h&igrave;nh gi&agrave;u sắc th&aacute;i, c&ocirc;ng nghệ&nbsp;Dolby Audio&nbsp;mang đến trải nghiệm &acirc;m thanh trong trẻo, hệ điều h&agrave;nh&nbsp;Google TV với thư viện ứng dụng phong ph&uacute;.</em></strong></p>

<h3>Tổng quan thiết kế</h3>

<p>&ndash;&nbsp;Google Tivi TCL 4K 43 inch 43P635&nbsp;thiết kế tr&agrave;n viền, mỏng gọn, h&ograve;a hợp liền mạch với kh&ocirc;ng gian nội thất được lựa chọn bố tr&iacute;.&nbsp;<strong>Ch&acirc;n đế chữ V &uacute;p ngược</strong>&nbsp;c&acirc;n bằng ở 2 b&ecirc;n th&acirc;n dưới&nbsp;<a href="https://www.dienmayxanh.com/tivi" target="_blank">tivi</a>, n&acirc;ng đỡ tivi vững v&agrave;ng, chắc chắn.</p>

<p>&ndash;&nbsp;M&agrave;n h&igrave;nh 43 inch, k&iacute;ch cỡ ph&ugrave; hợp để d&ugrave;ng trong c&aacute;c căn ph&ograve;ng kh&aacute;ch, ph&ograve;ng l&agrave;m việc, ph&ograve;ng ngủ c&oacute; diện t&iacute;ch vừa v&agrave; nhỏ.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/281934/google-tcl-4k-43-inch-43p635-310522-115049.jpg"><img alt="Google Tivi TCL 4K 43 inch 43P635 - Tổng quan thiết kế" src="https://cdn.tgdd.vn/Products/Images/1942/281934/google-tcl-4k-43-inch-43p635-310522-115049.jpg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa sản phẩm</em></p>

<h3>C&ocirc;ng nghệ h&igrave;nh ảnh</h3>

<p>&ndash;&nbsp;Tivi&nbsp;4K&nbsp;với 8 triệu điểm ảnh cho chất lượng khung h&igrave;nh sắc n&eacute;t để bạn xem c&aacute;c nội dung chi tiết hơn.</p>

<p>&ndash;&nbsp;C&ocirc;ng nghệ&nbsp;HDR10&nbsp;tăng độ s&aacute;ng, độ tương phản, m&agrave;u sắc dựa tr&ecirc;n cơ sở từng khung h&igrave;nh&nbsp;gi&uacute;p t&aacute;i hiện h&igrave;nh ảnh đa sắc th&aacute;i, s&aacute;ng đẹp tự nhi&ecirc;n.</p>

<p>&ndash;&nbsp;<strong>Smart HDR</strong>&nbsp;tối ưu chất lượng h&igrave;nh ảnh để bạn được thưởng thức c&aacute;c chi tiết đ&aacute;ng kinh ngạc như &yacute; tưởng của nh&agrave; s&aacute;ng tạo nội dung.</p>

<p>&ndash; Chế độ&nbsp;<strong>Game Mode</strong>&nbsp;cải tiến từ độ trễ đầu v&agrave;o đến đồ họa để mang đến trải nghiệm chơi game mượt m&agrave;, ch&acirc;n thực hơn cho người chơi.</p>

<p>Mời bạn xem th&ecirc;m:&nbsp;Những độ ph&acirc;n giải m&agrave;n h&igrave;nh phổ biến hiện nay tr&ecirc;n tivi</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/281934/google-tcl-4k-43-inch-43p635-010622-094225.jpg"><img alt="Google Tivi TCL 4K 43 inch 43P635 - Công nghệ hình ảnh" src="https://cdn.tgdd.vn/Products/Images/1942/281934/google-tcl-4k-43-inch-43p635-010622-094225.jpg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa sản phẩm</em></p>

<h3>C&ocirc;ng nghệ &acirc;m thanh</h3>

<p>&ndash; Tổng c&ocirc;ng suất loa<strong>&nbsp;20W</strong>,&nbsp;c&ocirc;ng nghệ Dolby Audio&nbsp;sử dụng định dạng &acirc;m thanh xử l&yacute; kỹ thuật số gi&uacute;p t&aacute;i tạo chất &acirc;m trong trẻo, r&otilde; r&agrave;ng, cho bạn tận hưởng c&aacute;c chương tr&igrave;nh thể thao, phim bom tấn Hollywood hấp dẫn, l&ocirc;i cuốn hơn.</p>

<p>&ndash;&nbsp;DTS&nbsp;t&aacute;i tạo &acirc;m thanh đa chiều để người nghe cảm nhận được sự ch&acirc;n thật trong từng cảnh quay, đoạn nhạc.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/281934/google-tcl-4k-43-inch-43p635-310522-115052.jpg"><img alt="Google Tivi TCL 4K 43 inch 43P635 - Công nghệ âm thanh" src="https://cdn.tgdd.vn/Products/Images/1942/281934/google-tcl-4k-43-inch-43p635-310522-115052.jpg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa sản phẩm</em></p>

<h3>Hệ điều h&agrave;nh</h3>

<p>&ndash; T&iacute;ch hợp hệ điều h&agrave;nh&nbsp;<strong>Google TV</strong>&nbsp;thiết kế giao diện hiện đại, th&acirc;n thiện với mọi người d&ugrave;ng, cho ph&eacute;p bạn t&igrave;m kiếm v&agrave; truy cập v&agrave;o ứng dụng y&ecirc;u th&iacute;ch dễ d&agrave;ng.</p>

<p>&ndash; Thư viện ứng dụng với&nbsp;<strong>hơn 700.000 ứng dụng&nbsp;</strong>hỗ trợ cả tiếng Việt v&agrave; tiếng Anh c&oacute; cả c&aacute;c ứng dụng quen thuộc như Clip TV, FPT Play, Netflix,&nbsp;VieON,&nbsp;YouTube, Google Play,&hellip; mở ra thế giới giải tr&iacute; phong ph&uacute; cho bạn thỏa sức kh&aacute;m ph&aacute; v&agrave; thư gi&atilde;n.</p>

<p>Xem th&ecirc;m:&nbsp;C&aacute;ch xem phim bằng tr&igrave;nh duyệt web tr&ecirc;n tivi</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/281934/google-tcl-4k-43-inch-43p635-010622-094624.jpg"><img alt="Google Tivi TCL 4K 43 inch 43P635 - Hệ điều hành" src="https://cdn.tgdd.vn/Products/Images/1942/281934/google-tcl-4k-43-inch-43p635-010622-094624.jpg" /></a></p>

<h3>Tiện &iacute;ch</h3>

<p>&ndash; Remote t&iacute;ch hợp micro để t&igrave;m kiếm bằng giọng n&oacute;i với trợ l&yacute; ảo&nbsp;Google Assistant hỗ trợ tiếng Việt tiện lợi.</p>

<p>&ndash;&nbsp;Gọi video qua&nbsp;<strong>Google Duo</strong>, ứng dụng mang đến cuộc gọi video chất lượng cao tr&ecirc;n m&agrave;n h&igrave;nh&nbsp;Google Tivi TCL, gi&uacute;p x&oacute;a nh&ograve;a khoảng c&aacute;ch địa l&yacute; để bạn v&agrave; gia đ&igrave;nh lu&ocirc;n kết nối, thoải m&aacute;i chia sẻ về c&aacute;c sự kiện trong cuộc sống hằng ng&agrave;y.</p>

<p>&ndash; T&iacute;nh năng&nbsp;<strong>Chromecast</strong>,&nbsp;<strong>T-Cast&nbsp;</strong>truyền ph&aacute;t video, nhạc từ&nbsp;điện thoại&nbsp;l&ecirc;n m&agrave;n h&igrave;nh&nbsp;tivi TCL&nbsp;cho bạn được trải nghiệm c&aacute;c nội dung giải tr&iacute; với h&igrave;nh ảnh r&otilde; n&eacute;t, &acirc;m thanh sinh động.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/281934/google-tcl-4k-43-inch-43p635-310522-115056.jpg"><img alt="Google Tivi TCL 4K 43 inch 43P635 - Tiện ích" src="https://cdn.tgdd.vn/Products/Images/1942/281934/google-tcl-4k-43-inch-43p635-310522-115056.jpg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa sản phẩm</em></p>

<p><em>Nh&igrave;n chung, với thiết kế sang trọng, cung cấp h&igrave;nh ảnh 4K chi tiết, t&aacute;i tạo khung h&igrave;nh sinh động với c&ocirc;ng nghệ HDR10, c&ocirc;ng nghệ Dolby Audio đem đến &acirc;m thanh r&otilde; r&agrave;ng, hệ điều h&agrave;nh Google TV hiện đại, gọi video qua Google Duo dễ d&agrave;ng, điều khiển giọng n&oacute;i tiếng Việt, Google Tivi TCL 4K 43 inch 43P635 rất xứng đ&aacute;ng c&oacute; mặt trong ng&ocirc;i nh&agrave; bạn.</em></p>
', N'tivi2.1.png', 12354, 1, 1, N'<table class="table" style="width:1099px">
	<tbody>
		<tr>
			<td>Loại tivi:</td>
			<td>Google Tivi43 inch4K</td>
		</tr>
		<tr>
			<td>Hệ điều h&agrave;nh</td>
			<td>Google TV</td>
		</tr>
		<tr>
			<td>Ứng dụng phổ biến</td>
			<td>Clip TVFPT PlayGalaxy Play (Fim+)MP3 ZingMyTVNetflixPOPS KidsSpotifyTr&igrave;nh duyệt webVieONYouTube</td>
		</tr>
		<tr>
			<td>C&ocirc;ng nghệ h&igrave;nh ảnh</td>
			<td>Dải m&agrave;u rộng Wide Color GamutGame ModeHDR10HLGSmart HDR</td>
		</tr>
		<tr>
			<td>Chiếu h&igrave;nh từ điện thoại l&ecirc;n TV</td>
			<td>AirPlay 2Screen Mirroring</td>
		</tr>
		<tr>
			<td>K&iacute;ch thước:</td>
			<td>Ngang 95.7 cm &ndash; Cao 61 cm &ndash; D&agrave;y 19 cm</td>
		</tr>
		<tr>
			<td>Năm ra mắt</td>
			<td>2022</td>
		</tr>
		<tr>
			<td>H&atilde;ng</td>
			<td>TCL</td>
		</tr>
	</tbody>
</table>
')
INSERT [dbo].[products] ([product_id], [category_id], [brand_id], [product_name], [price], [create_date], [description], [image], [quantity], [active], [supplier_id], [configuration]) VALUES (10, 3, 4, N'Smart Tivi Samsung 4K 55 inch UA55AU7002', 10590000, CAST(N'2023-09-25T00:00:00.000' AS DateTime), N'<p><em><strong>Smart Tivi Samsung 4K Crystal UHD 55 inch UA55BU8000</strong></em><strong><em>&nbsp;sở hữu</em></strong><em><strong>&nbsp;thiết kế tinh tế, m&agrave;n h&igrave;nh&nbsp;LED viền (Edge LED), VA LCD&nbsp;</strong></em><em><strong>si&ecirc;u mỏng đi c&ugrave;ng chất lượng h&igrave;nh ảnh 4K cực n&eacute;t, c&ocirc;ng nghệ OTS Lite điều chỉnh &acirc;m thanh theo chuyển động của vật thể, hệ điều h&agrave;nh&nbsp;</strong></em><em><strong>Tizen&trade;</strong></em><em><strong><em>&nbsp;</em>trực quan, th&acirc;n thiện v&agrave; v&ocirc; số c&aacute;c tiện &iacute;ch giải tr&iacute; đi k&egrave;m&nbsp;chắc chắn đ&aacute;p ứng nhu cầu giải tr&iacute; cơ bản của gia đ&igrave;nh bạn.</strong></em></p>

<h3>Tổng quan thiết kế</h3>

<p>&ndash;<strong>&nbsp;UA55BU8000&nbsp;</strong>mang thiết kế si&ecirc;u mỏng kết hợp ch&acirc;n đế bắt mắt v&agrave;&nbsp;<strong>c&oacute; thể điều chỉnh độ cao&nbsp;</strong>tạo tổng thể h&agrave;i h&ograve;a.&nbsp;<strong>Cạnh viền mỏng</strong>&nbsp;gi&uacute;p người d&ugrave;ng tập trung v&agrave;o nội dung tr&ecirc;n tivi m&agrave; kh&ocirc;ng bị viền t&aacute;c động.</p>

<p>&ndash; K&iacute;ch cỡ m&agrave;n h&igrave;nh&nbsp;<strong>55 inch</strong>&nbsp;sẽ l&agrave; điểm nhấn tuyệt vời ph&ugrave; hợp cho&nbsp;<strong>ph&ograve;ng họp, hội trường v&agrave; ph&ograve;ng kh&aacute;ch,&hellip;</strong></p>

<h3><a href="https://cdn.tgdd.vn/Products/Images/1942/273391/smart-samsung-4k-55-inch-ua55bu8000-250322-111749.jpg"><img alt="Smart Tivi Samsung 4K 55 inch UA55BU8000 - Thiết kế" src="https://cdn.tgdd.vn/Products/Images/1942/273391/smart-samsung-4k-55-inch-ua55bu8000-250322-111749.jpg" /></a></h3>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa sản phẩm</em></p>

<h3>C&ocirc;ng nghệ h&igrave;nh ảnh</h3>

<p>&ndash; Độ ph&acirc;n giải&nbsp;<strong>4K</strong>&nbsp;sắc n&eacute;t từng chi tiết.</p>

<p>&ndash;&nbsp;<strong>Dynamic Crystal Color</strong>&nbsp;c&ocirc;ng nghệ mang tỉ sắc m&agrave;u, những gam m&agrave;u&nbsp;<strong>ch&acirc;n thật, rực rỡ&nbsp;</strong>sẽ được mang l&ecirc;n&nbsp;khung h&igrave;nh gia đ&igrave;nh bạn.</p>

<p>&ndash;&nbsp;<strong>Bộ xử l&yacute; Crystal 4K&nbsp;</strong>tối ưu độ ph&acirc;n giải, độ tương phản cũng như m&agrave;u sắc, cho h&igrave;nh ảnh&nbsp;lu&ocirc;n ở<strong>&nbsp;chất lượng gần với 4K</strong>&nbsp;hiển thị từng chi tiết.</p>

<p>&ndash;&nbsp;<strong>HDR10+</strong>&nbsp;cho những cảnh phim&nbsp;<strong>c&oacute; chiều s&acirc;u</strong>, m&agrave;u sắc lu&ocirc;n được kiểm so&aacute;t ho&agrave;n hảo.</p>

<p>&ndash;&nbsp;<strong>Motion Xcelerator&nbsp;</strong>mang h&igrave;nh ảnh v&agrave;o c&aacute;c khung cảnh chuyển động, những trận game cũng như thước phim h&agrave;nh động mượt m&agrave;, chống x&eacute; h&igrave;nh.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/273391/smart-samsung-4k-55-inch-ua55bu8000-250322-111753.jpg"><img alt="Smart Tivi Samsung 4K 55 inch UA55BU8000 - Công nghệ hình ảnh" src="https://cdn.tgdd.vn/Products/Images/1942/273391/smart-samsung-4k-55-inch-ua55bu8000-250322-111753.jpg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa sản phẩm</em></p>

<h3>C&ocirc;ng nghệ &acirc;m thanh</h3>

<p>&ndash; C&ocirc;ng nghệ&nbsp;<strong>OTS Lite&nbsp;</strong>l&agrave;m cho<strong>&nbsp;</strong>&acirc;m thanh chuyển động theo vật thể, những l&agrave;n &acirc;m như vượt ra khỏi m&agrave;n h&igrave;nh, người d&ugrave;ng như đắm ch&igrave;m trong c&aacute;c cảnh phim h&agrave;nh động hấp dẫn.</p>

<p>&ndash;&nbsp;<strong>Q-Symphony</strong>&nbsp;tạo n&ecirc;n kh&ocirc;ng gian giải tr&iacute; đỉnh cao cho gia đ&igrave;nh bạn bằng c&aacute;ch&nbsp;kết nối loa tivi với loa thanh, &acirc;m thanh trong trẻo, sống động.</p>

<p>&ndash;<strong>&nbsp;Adaptive Sound</strong>&nbsp;điều chỉnh &acirc;m thanh theo điều kiện m&ocirc;i trường, bạn c&oacute; thể thưởng thức trọn vẹn c&aacute;c thanh &acirc;m m&agrave; kh&ocirc;ng lo bị t&aacute;c động bởi c&aacute;c yếu tố xung quanh.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/273391/smart-samsung-4k-55-inch-ua55bu8000-250322-111756.jpg"><img alt="Smart Tivi Samsung 4K 55 inch UA55BU8000 - Công nghệ âm thanh" src="https://cdn.tgdd.vn/Products/Images/1942/273391/smart-samsung-4k-55-inch-ua55bu8000-250322-111756.jpg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa sản phẩm</em></p>

<h3>Hệ điều h&agrave;nh</h3>

<p>&ndash;&nbsp;Hệ điều h&agrave;nh&nbsp;<strong>Tizen&trade;</strong>&nbsp;nổi bật với t&iacute;nh trực quan, th&ocirc;ng dụng, mang đến nhiều trải nghiệm mới lạ v&agrave;&nbsp;<strong>thao t&aacute;c dễ d&agrave;ng</strong>.</p>

<p>&ndash; Hệ điều h&agrave;nh d&agrave;nh cho&nbsp;<strong>tivi Samsung</strong>&nbsp;n&agrave;y được t&iacute;ch hợp nhiều ứng dụng giải tr&iacute; đỉnh cao như:&nbsp;Clip TV, FPT Play, Galaxy Play (Fim+), MP3 Zing, MyTV, Netflix, POPS Kids, Spotify, Tr&igrave;nh duyệt web, VieON, YouTube,&hellip;</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/273391/smart-samsung-4k-55-inch-ua55bu8000-250322-111755.jpg"><img alt="Smart Tivi Samsung 4K 55 inch UA55BU8000 - Hệ điều hành" src="https://cdn.tgdd.vn/Products/Images/1942/273391/smart-samsung-4k-55-inch-ua55bu8000-250322-111755.jpg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa sản phẩm</em></p>

<h3>Tiện &iacute;ch</h3>

<p>&ndash; Chiếu m&agrave;n h&igrave;nh từ điện thoại tiện lợi nhờ&nbsp;<strong>AirPlay 2, Screen Mirroring,&nbsp;Tap View</strong>, hỗ trợ bạn dễ d&agrave;ng đọc tin tức, tăng trải nghiệm xem phim hay chơi game tr&ecirc;n m&agrave;n h&igrave;nh lớn tivi.</p>

<p>&ndash;&nbsp;<strong>Google Assistant</strong>&nbsp;(c&oacute; tiếng Việt)&nbsp;gi&uacute;p t&igrave;m kiếm th&ocirc;ng tin, thực hiện y&ecirc;u cầu m&agrave; bạn đưa ra nhanh ch&oacute;ng, ch&iacute;nh x&aacute;c.</p>

<p>&ndash;&nbsp;<strong>Ứng dụng SmartThings</strong>&nbsp;kết nối tivi v&agrave; c&aacute;c thiết bị trong hệ sinh th&aacute;i Samsung, tiện gọn, dễ d&agrave;ng sử dụng.</p>

<p>&ndash;&nbsp;<strong>One Remote</strong>&nbsp;c&oacute; thể sạc bằng&nbsp;<strong>năng lượng mặt trời</strong>, điều khiển dễ d&agrave;ng, tiết kiệm chi ph&iacute;.</p>

<p>Xem th&ecirc;m :&nbsp;Hướng dẫn d&ograve; k&ecirc;nh tr&ecirc;n Smart TV Samsung</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1942/273391/smart-samsung-4k-55-inch-ua55bu8000-250322-111751.jpg"><img alt="Smart Tivi Samsung 4K 55 inch UA55BU8000 - Tiện ích" src="https://cdn.tgdd.vn/Products/Images/1942/273391/smart-samsung-4k-55-inch-ua55bu8000-250322-111751.jpg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa sản phẩm</em></p>

<p><em>Nh&igrave;n chung,&nbsp;</em><em>Smart Tivi Samsung 4K Crystal UHD 55 inch UA55BU8000​ với c&aacute;c&nbsp;</em><em>ưu điểm như thiết kế si&ecirc;u mỏng, độ ph&acirc;n giải 4K sắc n&eacute;t từng chi tiết, c&ocirc;ng nghệ OTS Lite &acirc;m thanh chuyển động theo h&igrave;nh ảnh ch&acirc;n thật, hệ điều h&agrave;nh&nbsp;</em><em>Tizen&trade;</em><em>&nbsp;giao diện đẹp mắt, thao t&aacute;c nhanh ch&oacute;ng v&agrave; c&aacute;c tiện &iacute;ch đỉnh cao đi k&egrave;m sẽ l&agrave; lựa chọn kh&ocirc;ng thể bỏ qua cho bạn v&agrave; gia đ&igrave;nh.</em></p>
', N'tivi3.png', 15, 1, 1, N'<table class="table" style="width:1099px">
	<tbody>
		<tr>
			<td>Loại tivi:</td>
			<td>Smart Tivi55 inch4K</td>
		</tr>
		<tr>
			<td>Hệ điều h&agrave;nh</td>
			<td>Tizen&trade;</td>
		</tr>
		<tr>
			<td>Ứng dụng phổ biến</td>
			<td>Clip TVFPT PlayGalaxy Play (Fim+)MP3 ZingMyTVNetflixPOPS KidsSpotifyTr&igrave;nh duyệt webVieONYouTube</td>
		</tr>
		<tr>
			<td>C&ocirc;ng nghệ h&igrave;nh ảnh</td>
			<td>Chuyển động mượt Motion XceleratorGiảm độ trễ chơi game Auto Low Latency Mode (ALLM)HDR10+Kiểm so&aacute;t đ&egrave;n nền UHD DimmingN&acirc;ng cấp độ tương phản Contrast EnhancerPurColor</td>
		</tr>
		<tr>
			<td>Chiếu h&igrave;nh từ điện thoại l&ecirc;n TV</td>
			<td>AirPlay 2Screen Mirroring</td>
		</tr>
		<tr>
			<td>K&iacute;ch thước:</td>
			<td>Ngang 123.06 cm &ndash; Cao 78.63 cm &ndash; D&agrave;y 25.82 cm</td>
		</tr>
		<tr>
			<td>Năm ra mắt</td>
			<td>2022</td>
		</tr>
		<tr>
			<td>H&atilde;ng</td>
			<td>Samsung</td>
		</tr>
	</tbody>
</table>
')
INSERT [dbo].[products] ([product_id], [category_id], [brand_id], [product_name], [price], [create_date], [description], [image], [quantity], [active], [supplier_id], [configuration]) VALUES (11, 1, 5, N'Máy lạnh Panasonic Inverter 1.5 HP CU/CS-XU12ZKH-8', 17390000, CAST(N'2022-11-22T00:00:00.000' AS DateTime), N'<h3>Thiết kế</h3>

<p><strong>D&agrave;n lạnh:</strong></p>

<p>D&agrave;n lạnh được thiết kế m&agrave;u trắng với lớp vỏ bằng nhựa cao cấp v&agrave; bền bỉ, ph&ugrave; hợp cho mọi kh&ocirc;ng gian lắp đặt. Ngo&agrave;i ra, mặt trước d&agrave;n lạnh c&oacute; thể th&aacute;o rời dễ d&agrave;ng để thuận tiện cho việc vệ sinh thiết bị theo định kỳ.</p>

<p><strong>D&agrave;n n&oacute;ng:</strong></p>

<p>&ndash; D&agrave;n n&oacute;ng của&nbsp;m&aacute;y lạnh&nbsp;c&oacute; h&igrave;nh hộp chữ nhật nằm ngang, m&agrave;u trắng với chất liệu vỏ m&aacute;y c&oacute; thể chịu được tốt c&aacute;c điều kiện kh&iacute; hậu khi lắp đặt ngo&agrave;i trời.</p>

<p>&ndash; D&agrave;n n&oacute;ng v&agrave; d&agrave;n lạnh đều sử dụng&nbsp;<strong>l&aacute; tản nhiệt l&agrave;m bằng nh&ocirc;m</strong>, c&ugrave;ng với&nbsp;<strong>ống dẫn gas bằng đồng</strong>&nbsp;bền bỉ, c&oacute; khả năng truyền nhiệt nhanh.</p>

<p><img alt="Máy lạnh Panasonic Inverter 1.5 HP CU/CS-XU12ZKH-8 - Thiết kế dàn lạnh hiện đại, tinh tế và dàn nóng hoạt động bền bỉ" src="https://cdn.tgdd.vn/Products/Images/2002/303868/may-lanh-panasonic-inverter-15-hp-cu-cs-xu12zkh-8-1-1.jpg" /></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<h3>C&ocirc;ng nghệ l&agrave;m lạnh</h3>

<p>&ndash; Chiếc&nbsp;m&aacute;y lạnh Panasonic&nbsp;n&agrave;y được thiết kế với&nbsp;<strong>c&ocirc;ng suất 1.5 HP</strong>, ph&ugrave; hợp l&agrave;m m&aacute;t cho căn ph&ograve;ng c&oacute; diện t&iacute;ch từ&nbsp;<strong>15 &ndash; 20m&sup2;</strong>.</p>

<p>&ndash;&nbsp;Chế độ l&agrave;m lạnh nhanh iAuto-X&nbsp;<strong>với c&ocirc;ng nghệ P-TECH</strong>: Sau khi k&iacute;ch hoạt, m&aacute;y lạnh sẽ hoạt động với c&ocirc;ng suất cao nhất c&ugrave;ng với tốc độ quạt mạnh, đồng thời thổi luồng kh&iacute; lạnh l&ecirc;n tr&ecirc;n trần nh&agrave;, l&agrave;m cho&nbsp;<strong>nhiệt độ căn ph&ograve;ng được hạ xuống nhanh ch&oacute;ng đến</strong>&nbsp;<strong>25%</strong>, gi&uacute;p người d&ugrave;ng cảm nhận bầu kh&ocirc;ng kh&iacute; m&aacute;t lạnh ngay lập tức m&agrave; vẫn đảm bảo sự thoải m&aacute;i.</p>

<p>&ndash;&nbsp;<strong>Chế độ ngủ đ&ecirc;m Sleep</strong>: M&aacute;y lạnh c&oacute; khả năng tự động tăng nhiệt độ khi trời về đ&ecirc;m, gi&uacute;p nhiệt độ căn ph&ograve;ng ph&ugrave; hợp với nhiệt độ cơ thể người nằm ngủ, từ đ&oacute;&nbsp;<strong>tr&aacute;nh g&acirc;y cảm gi&aacute;c lạnh buốt v&agrave; mang lại giấc ngủ ngon</strong>&nbsp;cho người d&ugrave;ng. Chế độ n&agrave;y ph&ugrave; hợp cho người lớn tuổi, trẻ em v&agrave; phụ nữ với cơ địa kh&aacute; nhạy cảm.</p>

<p><img alt="Máy lạnh Panasonic Inverter 1.5 HP CU/CS-XU12ZKH-8 - Công suất 1.5 HP phù hợp diện tích căn phòng từ 15 - 20m2" src="https://cdn.tgdd.vn/Products/Images/2002/303868/may-lanh-panasonic-inverter-15-hp-cu-cs-xu12zkh-8-2.jpg" /></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<h3>Cơ chế thổi gi&oacute;</h3>

<p>M&aacute;y lạnh Panasonic 1.5 HP c&oacute; thể&nbsp;t&ugrave;y chỉnh c&aacute;nh đảo gi&oacute; l&ecirc;n xuống tr&aacute;i phải tự động, gi&uacute;p hơi lạnh lan tỏa đều khắp khu vực cần l&agrave;m m&aacute;t.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/2002/303868/may-lanh-panasonic-inverter-15-hp-cu-cs-xu12zkh-8-3.jpg"><img alt="Máy lạnh Panasonic Inverter 1.5 HP CU/CS-XU12ZKH-8 - Tủy chỉnh cánh đảo gió lên xuống, trái phải tự động" src="https://cdn.tgdd.vn/Products/Images/2002/303868/may-lanh-panasonic-inverter-15-hp-cu-cs-xu12zkh-8-3.jpg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<h3>C&aacute;c c&ocirc;ng nghệ tiết kiệm điện</h3>

<p>&ndash; Sản phẩm được t&iacute;ch hợp c&ocirc;ng nghệ Inverter v&agrave; chế độ Eco, cho hiệu quả tiết kiệm điện vượt trội trong suốt qu&aacute; tr&igrave;nh hoạt động. Cụ thể:</p>

<p>+&nbsp;C&ocirc;ng nghệ Inverter: C&oacute; khả năng điều chỉnh m&aacute;y n&eacute;n, vừa gi&uacute;p duy tr&igrave; nhiệt độ lạnh tối ưu trong căn ph&ograve;ng, vừa&nbsp;<strong>mang lại hiệu quả tiết kiệm điện v&agrave; vận h&agrave;nh &ecirc;m &aacute;i</strong>.</p>

<p>+&nbsp;Chế độ ECO t&iacute;ch hợp A.I: C&oacute; khả năng điều chỉnh c&ocirc;ng suất hoạt động của m&aacute;y lạnh sao cho ph&ugrave; hợp với thời gian l&agrave;m lạnh. M&aacute;y lạnh nhanh ch&oacute;ng đạt đến nhiệt độ c&agrave;i đặt, đồng thời vẫn c&oacute; thể l&agrave;m giảm c&ocirc;ng suất hoạt động của m&aacute;y n&eacute;n từ&nbsp;<strong>5 &ndash; 50%,&nbsp;</strong>tiết kiệm điện đ&aacute;ng kể.</p>

<p>&ndash; M&aacute;y lạnh Panasonic CU/CS-XU12ZKH-8 đạt nh&atilde;n năng lượng<strong>&nbsp;5 sao</strong>&nbsp;với hiệu suất<strong>&nbsp;6.32&nbsp;</strong>v&agrave; sử dụng điện khoảng&nbsp;<a href="https://www.dienmayxanh.com/kinh-nghiem-hay/cong-suat-tieu-thu-dien-toi-da-855107" target="_blank">0.95 kW/h</a>.</p>

<p><img alt="Máy lạnh Panasonic Inverter 1.5 HP CU/CS-XU12ZKH-8 - Có khả năng tiết kiệm điện đáng kể nhờ sử dụng công nghệ Inverter và chế độ ECO tích hợp AI" src="https://cdn.tgdd.vn/Products/Images/2002/303868/may-lanh-panasonic-inverter-15-hp-cu-cs-xu12zkh-8-5.jpg" /></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<h3>Khả năng lọc kh&ocirc;ng kh&iacute; &ndash; sức khoẻ</h3>

<p>&ndash;&nbsp;<strong>C&ocirc;ng nghệ lọc kh&ocirc;ng kh&iacute; Nanoe&trade; X</strong>: L&agrave; c&ocirc;ng nghệ lọc kh&ocirc;ng kh&iacute; độc quyền của Panasonic, c&oacute; khả năng giải ph&oacute;ng ra c&aacute;c ph&acirc;n tử Nanoe X c&oacute; k&iacute;ch thước si&ecirc;u nhỏ để ức chế sự ph&aacute;t triển của vi khuẩn v&agrave; virus đến&nbsp;<strong>99%</strong>, đồng thời loại bỏ c&aacute;c chất g&acirc;y dị ứng v&agrave; m&ugrave;i h&ocirc;i tối ưu,&nbsp;<strong>mang lại bầu kh&ocirc;ng kh&iacute; sạch khuẩn v&agrave; an to&agrave;n</strong>&nbsp;cho người d&ugrave;ng.</p>

<p>Hơn nữa, c&ocirc;ng nghệ n&agrave;y c&ograve;n c&oacute; thể tạo ra c&aacute;c ph&acirc;n tử nước, nhờ đ&oacute; c&oacute; thể cấp ẩm cũng như&nbsp;<strong>tr&aacute;nh cho da v&agrave; t&oacute;c của người sử dụng kh&ocirc;ng bị kh&ocirc;</strong>&nbsp;trong suốt thời gian sinh hoạt trong ph&ograve;ng m&aacute;y lạnh.</p>

<p>&ndash;&nbsp;C&ocirc;ng nghệ Nanoe-G lọc bụi mịn PM 2.5: Giải ph&oacute;ng ra c&aacute;c ph&acirc;n tử Nanoe-G với k&iacute;ch thước si&ecirc;u nhỏ, để loại bỏ c&aacute;c hạt lơ lửng b&ecirc;n trong kh&ocirc;ng kh&iacute;, gồm cả hạt bụi mịn PM 2.5,&nbsp;<strong>mang lại khả năng lọc sạch bụi bẩn</strong>&nbsp;đến&nbsp;<strong>99%</strong>, nhờ đ&oacute; gi&uacute;p bảo vệ sức khỏe h&ocirc; hấp cho cả gia đ&igrave;nh người d&ugrave;ng tốt hơn.</p>

<p>Panasonic c&ugrave;ng tổ chức Texcell đ&atilde; cho kết quả thử nghiệm c&ocirc;ng nghệ Nanoe&trade; X c&oacute; khả năng ức chế 91.4% khả năng hoạt động của SARS-CoV-2 trong kh&ocirc;ng gian k&iacute;n 6.7m&sup3; nếu bật m&aacute;y lạnh trong v&ograve;ng 8 giờ. Thử nghiệm n&agrave;y được thực hiện trong điều kiện ph&ograve;ng th&iacute; nghiệm k&iacute;n, kh&ocirc;ng được thiết kế để đ&aacute;nh gi&aacute; trong kh&ocirc;ng gian sống kh&ocirc;ng được kiểm so&aacute;t.</p>

<p>Giấy chứng nhận v&agrave; kết quả kiểm nghiệm của tổ chức Nghi&ecirc;n cứu Tiếp x&uacute;c To&agrave;n cầu Texcell về khả năng ức chế SARS-CoV-2 của c&ocirc;ng nghệ Nanoe&trade; X trong ph&ograve;ng th&iacute; nghiệm.</p>

<p><em>* Texcell l&agrave; tổ chức đầu ti&ecirc;n của viện Pasteur Paris được th&agrave;nh lập v&agrave;o năm 1997 chuy&ecirc;n nghi&ecirc;n cứu to&agrave;n cầu về thử nghiệm vi r&uacute;t, loại bỏ vi r&uacute;t, cung cấp miễn dịch,&hellip;. Với hơn 30 năm kinh nghiệm v&agrave; c&oacute; trụ sở tại Viện Pasteur Paris, Texcell được c&ocirc;ng nhận về chuy&ecirc;n m&ocirc;n gi&aacute;m định virus với một loạt c&aacute;c quy tr&igrave;nh để ph&aacute;t hiện c&aacute;c t&aacute;c nh&acirc;n tiềm ẩn.</em></p>

<h3>Tiện &iacute;ch</h3>

<p>&ndash;&nbsp;<strong>Chế độ kiểm so&aacute;t độ ẩm</strong>: Khi k&iacute;ch hoạt&nbsp;chế độ Dry&nbsp;c&ugrave;ng với việc sử dụng&nbsp;<strong>cảm biến Humidity Sensor</strong>,&nbsp;m&aacute;y lạnh Panasonic Inverter&nbsp;n&agrave;y c&oacute; thể&nbsp;<strong>kiểm so&aacute;t độ ẩm tối ưu</strong>&nbsp;để mang lại sự thoải m&aacute;i cho người d&ugrave;ng v&agrave; bảo vệ đồ d&ugrave;ng trong căn ph&ograve;ng tr&aacute;nh bị ẩm mốc.</p>

<p>&ndash;&nbsp;<strong>Chức năng tự chẩn đo&aacute;n lỗi</strong>: Gi&uacute;p người d&ugrave;ng x&aacute;c định v&agrave; khắc phục được lỗi m&aacute;y lạnh sớm nhất c&oacute; thể, từ đ&oacute; kh&ocirc;ng l&agrave;m ảnh hưởng đến việc sử dụng thiết bị cũng như&nbsp;<strong>tiết kiệm thời gian sửa chữa</strong>&nbsp;khi m&aacute;y lạnh gặp phải lỗi.</p>

<p>&ndash;&nbsp;<strong>Vệ sinh b&ecirc;n trong d&agrave;n lạnh Inside Cleaning</strong>: Tr&aacute;nh nấm mốc v&agrave; vi khuẩn ph&aacute;t triển, l&agrave;m ảnh hưởng đến hiệu quả l&agrave;m lạnh v&agrave; độ bền của d&agrave;n lạnh.</p>

<p>&ndash;&nbsp;Điều khiển bằng điện thoại, c&oacute; wifi: M&aacute;y lạnh Panasonic Inverter 1.5 HP c&oacute; khả năng kết nối wifi v&agrave; người d&ugrave;ng c&oacute; thể&nbsp;<strong>điều khiển m&aacute;y lạnh từ xa th&ocirc;ng qua ứng dụng được c&agrave;i đặt tr&ecirc;n điện thoại</strong>&nbsp;mọi l&uacute;c mọi nơi. Nhờ đ&oacute;, người d&ugrave;ng c&oacute; thể theo d&otilde;i điện năng ti&ecirc;u thụ, nhanh ch&oacute;ng ph&aacute;t hiện sự cố,&hellip; v&agrave; điều khiển chức năng m&aacute;y lạnh dễ d&agrave;ng.</p>

<p><em>T&oacute;m lại, m&aacute;y lạnh Panasonic Inverter 1.5 HP CU/CS-XU12ZKH-8 gi&uacute;p bảo vệ sức khỏe h&ocirc; hấp của người d&ugrave;ng tối ưu, ph&ugrave; hợp cho những ai c&oacute; sức khỏe nhạy cảm như trẻ nhỏ, người lớn tuổi, phụ nữ v&agrave; người c&oacute; vấn đề về h&ocirc; hấp. Đồng thời, sản phẩm n&agrave;y cũng ph&ugrave; hợp cho c&aacute;c hộ gia đ&igrave;nh c&oacute; nhiều ph&ograve;ng, kinh doanh trong lĩnh vực kh&aacute;ch sạn &ndash; nh&agrave; h&agrave;ng,&hellip; nhờ khả năng kiểm so&aacute;t hoạt động của m&aacute;y lạnh từ xa th&ocirc;ng qua ứng dụng tr&ecirc;n điện thoại.&nbsp;</em></p>
', N'tivi1.png', 4, 1, 1, N'<table class="table" style="width:1099px">
	<tbody>
		<tr>
			<td>Loại m&aacute;y:</td>
			<td>1 chiều (chỉ l&agrave;m lạnh)C&oacute; Inverter</td>
		</tr>
		<tr>
			<td>C&ocirc;ng suất l&agrave;m lạnh:</td>
			<td>1.5 HP &ndash; 11.900 BTU</td>
		</tr>
		<tr>
			<td>Phạm vi l&agrave;m lạnh hiệu quả:</td>
			<td>Từ 15 &ndash; 20m&sup2; (từ 40 đến 60 m&sup3;)</td>
		</tr>
		<tr>
			<td>Lọc bụi, kh&aacute;ng khuẩn, khử m&ugrave;i:</td>
			<td>C&ocirc;ng nghệ lọc kh&ocirc;ng kh&iacute; Nanoe&trade; X thế hệ 3Nanoe-G lọc bụi mịn PM 2.5</td>
		</tr>
		<tr>
			<td>C&ocirc;ng nghệ tiết kiệm điện:</td>
			<td>ECO t&iacute;ch hợp A.IInverter</td>
		</tr>
		<tr>
			<td>Tiện &iacute;ch:</td>
			<td>Chế độ kiểm so&aacute;t độ ẩm&nbsp;Chế độ ngủ đ&ecirc;m Sleep cho người gi&agrave;, trẻ nhỏ&nbsp;Chức năng tự chẩn đo&aacute;n lỗi&nbsp;Hoạt động si&ecirc;u &ecirc;m Quiet&nbsp;Hẹn giờ bật tắt m&aacute;yTự khởi động lại khi c&oacute; điện&nbsp;Vệ sinh b&ecirc;n trong d&agrave;n lạnh: Inside Cleaning&nbsp;Điều khiển bằng điện thoại, c&oacute; wifi</td>
		</tr>
		<tr>
			<td>Ti&ecirc;u thụ điện:</td>
			<td>0.95 kW/h5 sao (Hiệu suất năng lượng 6.32)</td>
		</tr>
		<tr>
			<td>H&atilde;ng</td>
			<td>Panasonic</td>
		</tr>
	</tbody>
</table>

<div class="ddict_btn" style="top: 24px; left: 33.7375px;"><img src="chrome-extension://bpggmmljdiliancllaapiggllnkbjocb/logo/48.png" /></div>
')
INSERT [dbo].[products] ([product_id], [category_id], [brand_id], [product_name], [price], [create_date], [description], [image], [quantity], [active], [supplier_id], [configuration]) VALUES (18, 1, 5, N'Máy lạnh Panasonic Inverter 1 HP CU/CS-XU9ZKH-8', 12490000, CAST(N'2023-10-02T00:00:00.000' AS DateTime), N'<h3>Tổng quan thiết kế</h3>

<p>D&agrave;n lạnh</p>

<p>&ndash;&nbsp;M&aacute;y lạnh Panasonic&nbsp;c&oacute; vẻ ngo&agrave;i thời thượng với t&ocirc;ng m&agrave;u trắng thuần khiết, đường viền m&agrave;u bạc nổi bật tạo điểm nhấn cho tổng thể thiết kế th&ecirc;m đẹp mắt, sang trọng. Kiểu d&aacute;ng h&igrave;nh chữ nhật th&iacute;ch hợp lắp đặt trong kh&ocirc;ng gian ph&ograve;ng kh&aacute;ch, ph&ograve;ng ngủ, ph&ograve;ng l&agrave;m việc của bạn.</p>

<p>D&agrave;n n&oacute;ng</p>

<p>&ndash; Kiểu d&aacute;ng tối giản, c&oacute; lớp vỏ m&aacute;y chất liệu tốt cho độ bền cao, rắn chắc, bảo vệ c&aacute;c chi tiết an to&agrave;n khỏi t&aacute;c động của c&aacute;c yếu tố từ m&ocirc;i trường b&ecirc;n ngo&agrave;i.</p>

<p>&ndash; Trang bị&nbsp;<strong>ống dẫn gas bằng đồng, l&aacute; tản nhiệt l&agrave;m từ nh&ocirc;m</strong>&nbsp;dẫn nhiệt tốt, hạn chế ăn m&ograve;n, duy tr&igrave; hiệu quả l&agrave;m lạnh tối ưu, sử dụng d&agrave;i l&acirc;u.</p>

<p><img alt="Máy lạnh Panasonic Inverter 1 HP CU/CS-XU9ZKH-8 - Tổng quan thiết kế" src="https://cdn.tgdd.vn/Products/Images/2002/303867/may-lanh-panasonic-inverter-1-hp-cu-cs-xu9zkh-8-190323-115519.jpg" /></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<h3>C&ocirc;ng nghệ l&agrave;m lạnh</h3>

<p>&ndash; Đ&aacute;p ứng nhu cầu l&agrave;m lạnh trong phạm vi dưới 15m&sup2; (từ 30 đến 45m&sup3;) với c&ocirc;ng suất&nbsp;<strong>1 HP.</strong></p>

<p>&ndash;&nbsp;Hỗ trợ c&ocirc;ng nghệ&nbsp;iAuto-X&nbsp;với sự kết hợp của c&ocirc;ng nghệ P-TECH đẩy c&ocirc;ng suất m&aacute;y n&eacute;n v&agrave; quạt thổi gi&oacute; l&ecirc;n cao, cho nhiệt độ trong ph&ograve;ng nhanh ch&oacute;ng đạt đến mức nhiệt bạn đ&atilde; thiết lập trong thời gian ngắn, cho bạn cảm thấy m&aacute;t mẻ tức th&igrave;.</p>

<p><img alt="Máy lạnh Panasonic Inverter 1 HP CU/CS-XU9ZKH-8 - Công nghệ làm lạnh  " src="https://cdn.tgdd.vn/Products/Images/2002/303867/may-lanh-panasonic-inverter-1-hp-cu-cs-xu9zkh-8-190323-115541.jpg" /></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<h3>Cơ chế thổi gi&oacute;</h3>

<p>&ndash; Thiết bị&nbsp;<strong>tuỳ chỉnh điều khiển l&ecirc;n xuống tr&aacute;i phải tự động</strong>&nbsp;cho khả năng lan tỏa hơi lạnh đồng đều v&agrave; rộng khắp gian ph&ograve;ng, để mọi người sinh hoạt trong kh&ocirc;ng gian ph&ograve;ng đều cảm thấy dễ chịu, thư th&aacute;i.</p>

<p><img alt="Máy lạnh Panasonic Inverter 1 HP CU/CS-XU9ZKH-8 - Cơ chế thổi gió" src="https://cdn.tgdd.vn/Products/Images/2002/303867/may-lanh-panasonic-inverter-1-hp-cu-cs-xu9zkh-8-190323-115552.jpg" /></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<h3>C&ocirc;ng nghệ tiết kiệm điện</h3>

<p>&ndash; C&ocirc;ng nghệ&nbsp;Inverter&nbsp;sử dụng m&aacute;y n&eacute;n biến tần điều chỉnh v&ograve;ng quay sao cho nhiệt độ l&agrave;m lạnh được duy tr&igrave; ổn định, tiết kiệm điện năng, thiết bị vận h&agrave;nh &ecirc;m &aacute;i, hiệu quả.</p>

<p>&ndash;&nbsp;ECO t&iacute;ch hợp AI, c&ocirc;ng nghệ&nbsp;tự động c&acirc;n chỉnh mức giảm c&ocirc;ng suất th&iacute;ch hợp từ 5% &ndash; 50% v&agrave;&nbsp;thời gian cần l&agrave;m lạnh gi&uacute;p nhiệt độ trong ph&ograve;ng ở mức ph&ugrave; hợp nhưng giảm ti&ecirc;u hao điện năng tối đa.</p>

<p>&ndash;&nbsp;<a href="https://www.dienmayxanh.com/may-lanh" target="_blank">M</a>&aacute;<a href="https://www.dienmayxanh.com/may-lanh" target="_blank">y lạnh</a>&nbsp;sử dụng&nbsp;gas&nbsp;<strong>R-32</strong>&nbsp;gi&uacute;p l&agrave;m sạch s&acirc;u, giảm lượng kh&iacute; thải l&ecirc;n tới 75%, hạn chế hao ph&iacute; năng lượng, tăng cường bảo vệ m&ocirc;i trường sống.</p>

<p>&ndash; Nh&atilde;n năng lượng:&nbsp;<strong>5 sao&nbsp;</strong>(hiệu suất năng lượng 6.16).</p>

<p><img alt="Máy lạnh Panasonic Inverter 1 HP CU/CS-XU9ZKH-8 - Công nghệ tiết kiệm điện" src="https://cdn.tgdd.vn/Products/Images/2002/303867/may-lanh-panasonic-inverter-1-hp-cu-cs-xu9zkh-8-190323-115821.jpg" /></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<h3>Khả năng lọc kh&ocirc;ng kh&iacute;</h3>

<p>&ndash; C&ocirc;ng nghệ&nbsp;<strong>Nanoe-G</strong>&nbsp;lọc sạch bụi bẩn, bụi mịn PM 2.5, cho kh&ocirc;ng gian ph&ograve;ng trong l&agrave;nh hơn.</p>

<p>&ndash; C&ocirc;ng nghệ&nbsp;<strong>Nanoe&trade; X</strong>&nbsp;loại bỏ 5 loại m&ugrave;i h&ocirc;i l&agrave;&nbsp;<strong>m&ugrave;i ẩm mốc, kh&oacute;i thuốc, m&ugrave;i vật nu&ocirc;i, thức ăn nướng v&agrave; m&ugrave;i mồ h&ocirc;i</strong>, ti&ecirc;u diệt vi khuẩn, virus, đảm bảo an to&agrave;n cho sức khỏe của gia đ&igrave;nh.</p>

<p><img alt="Máy lạnh Panasonic Inverter 1 HP CU/CS-XU9ZKH-8 - Khả năng lọc không khí " src="https://cdn.tgdd.vn/Products/Images/2002/303867/may-lanh-panasonic-inverter-1-hp-cu-cs-xu9zkh-8-190323-115702.jpg" /></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<p>Panasonic c&ugrave;ng tổ chức Texcell đ&atilde; cho kết quả thử nghiệm c&ocirc;ng nghệ Nanoe&trade;️ X c&oacute; khả năng ức chế 91.4% khả năng hoạt động của SARS-CoV-2 trong kh&ocirc;ng gian k&iacute;n 6.7m&sup3; nếu bật m&aacute;y lạnh trong v&ograve;ng 8 giờ. Thử nghiệm n&agrave;y được thực hiện trong điều kiện ph&ograve;ng th&iacute; nghiệm k&iacute;n, kh&ocirc;ng được thiết kế để đ&aacute;nh gi&aacute; trong kh&ocirc;ng gian sống kh&ocirc;ng được kiểm so&aacute;t.</p>

<p><strong>Dưới đ&acirc;y l&agrave; kết quả kiểm nghiệm v&agrave; giấy chứng nhận c&ocirc;ng nghệ Nanoe&trade;️ X c&oacute; khả năng ức chế vi-r&uacute;t Corona trong điều kiện ph&ograve;ng th&iacute; nghiệm.</strong></p>

<p><img alt="Máy lạnh Panasonic Inverter 1 HP CU/CS-XU9ZKH-8 - Công nghệ Nanoe™️ X" src="https://cdn.tgdd.vn/Products/Images/2002/303867/may-lanh-panasonic-inverter-1-hp-cu-cs-xu9zkh-8-190323-115728.jpg" /></p>

<p><em>Kết quả kiểm nghiệm c&ocirc;ng nghệ Nanoe&trade;️ X c&oacute; khả năng ức chế vi-r&uacute;t Corona trong điều kiện ph&ograve;ng th&iacute; nghiệm.</em></p>

<p><img alt="Máy lạnh Panasonic Inverter 1 HP CU/CS-XU9ZKH-8 - Công nghệ Nanoe™️ X 2" src="https://cdn.tgdd.vn/Products/Images/2002/303867/may-lanh-panasonic-inverter-1-hp-cu-cs-xu9zkh-8-190323-115753.jpg" /></p>

<p><em>Giấy chứng nhận c&ocirc;ng nghệ Nanoe&trade;️ X c&oacute; khả năng ức chế vi-r&uacute;t Corona trong điều kiện ph&ograve;ng th&iacute; nghiệm.</em></p>

<p><em>* Texcell l&agrave; tổ chức đầu ti&ecirc;n của viện Pasteur Paris được th&agrave;nh lập v&agrave;o năm 1997 chuy&ecirc;n nghi&ecirc;n cứu to&agrave;n cầu về thử nghiệm vi r&uacute;t, loại bỏ vi r&uacute;t, cung cấp miễn dịch,&hellip;. Với hơn 30 năm kinh nghiệm v&agrave; c&oacute; trụ sở tại Viện Pasteur Paris, Texcell được c&ocirc;ng nhận về chuy&ecirc;n m&ocirc;n gi&aacute;m định virus với một loạt c&aacute;c quy tr&igrave;nh để ph&aacute;t hiện c&aacute;c t&aacute;c nh&acirc;n tiềm ẩn.</em></p>

<h3>Tiện &iacute;ch</h3>

<p>&ndash;&nbsp;Điều khiển bằng điện thoại, c&oacute; wifi: thiết bị c&oacute; thể kết nối với điện thoại qua wifi v&agrave; t&ugrave;y chỉnh chức năng&nbsp;m&aacute;y lạnh Inverter&nbsp;từ xa linh hoạt th&ocirc;ng qua ứng dụng h&atilde;ng cung cấp.</p>

<p>&ndash;&nbsp;<strong>Chế độ kiểm so&aacute;t độ ẩm</strong>: cho kh&ocirc;ng gian ph&ograve;ng th&ocirc;ng tho&aacute;ng, kh&ocirc; r&aacute;o, kh&ocirc;ng c&ograve;n t&igrave;nh trạng ẩm ướt kh&oacute; chịu.</p>

<p>&ndash;&nbsp;<strong>Vệ sinh b&ecirc;n trong d&agrave;n lạnh &ndash;&nbsp;Inside Cleaning</strong>: loại bỏ hơi ẩm trong d&agrave;n lạnh, vệ sinh tự động, đảm bảo khả năng l&agrave;m lạnh v&agrave; hoạt động của thiết bị trơn tru, hiệu quả.</p>

<p>&ndash;&nbsp;Chức năng tự chẩn đo&aacute;n lỗi: ph&aacute;t hiện sự cố ở m&aacute;y lạnh nhanh ch&oacute;ng, từ đ&oacute; người d&ugrave;ng dễ d&agrave;ng nhận diện, t&igrave;m kiếm, khắc phục ph&ugrave; hợp, gi&uacute;p tiết kiệm chi ph&iacute; sửa chữa hơn.</p>

<p>Ngo&agrave;i ra c&ograve;n c&oacute; c&aacute;c tiện &iacute;ch kh&aacute;c như:&nbsp;hẹn giờ bật tắt m&aacute;y,&nbsp;tự khởi động lại khi c&oacute; điện,&hellip;</p>

<p><img alt="Máy lạnh Panasonic Inverter 1 HP CU/CS-XU9ZKH-8 - Tiện ích" src="https://cdn.tgdd.vn/Products/Images/2002/303867/may-lanh-panasonic-inverter-1-hp-cu-cs-xu9zkh-8-190323-115813.jpg" /></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<p><em>Nh&igrave;n chung, chiếc m&aacute;y lạnh Panasonic Inverter 1 HP CU/CS-XU9ZKH-8 t&iacute;ch hợp nhiều c&ocirc;ng nghệ gi&uacute;p tăng tốc độ l&agrave;m lạnh, giữ kh&ocirc;ng kh&iacute; ph&ograve;ng trong sạch, tinh khiết, tiết kiệm điện năng với c&aacute;c c&ocirc;ng nghệ iAuto-X, ECO t&iacute;ch hợp A.I, Inverter, Nanoe&trade; X, Nanoe-G, điều khiển bằng điện thoại th&ocirc;ng qua wifi,&hellip;</em></p>
', N'maylanh1.png', 1, 1, 1, N'<h2>TH&Ocirc;NG SỐ KỸ THUẬT</h2>

<table class="table" style="width:1099px">
	<tbody>
		<tr>
			<td>Loại m&aacute;y:</td>
			<td>1 chiều (chỉ l&agrave;m lạnh)C&oacute; Inverter</td>
		</tr>
		<tr>
			<td>C&ocirc;ng suất l&agrave;m lạnh:</td>
			<td>1.5 HP &ndash; 12.000 BTU</td>
		</tr>
		<tr>
			<td>Phạm vi l&agrave;m lạnh hiệu quả:</td>
			<td>Từ 15 &ndash; 20m&sup2; (từ 40 đến 60 m&sup3;)</td>
		</tr>
		<tr>
			<td>Lọc bụi, kh&aacute;ng khuẩn, khử m&ugrave;i:</td>
			<td>Lọc kh&iacute; Ion Plasmaster tăng cường&nbsp;M&agrave;ng lọc dị ứngM&agrave;ng lọc sơ cấp&nbsp;Tạo ion lọc kh&ocirc;ng kh&iacute;</td>
		</tr>
		<tr>
			<td>C&ocirc;ng nghệ tiết kiệm điện:</td>
			<td>Dual inverterEnergy Ctrl &ndash; Kiểm so&aacute;t năng lượng chủ động 4 mức</td>
		</tr>
		<tr>
			<td>Tiện &iacute;ch:</td>
			<td>Chế độ ngủ đ&ecirc;m tr&aacute;nh buốt&nbsp;Chức năng tự chẩn đo&aacute;n lỗi&nbsp;Chức năng tự l&agrave;m sạch&nbsp;C&ocirc;ng nghệ Gold-Fin chống ăn m&ograve;n&nbsp;Hẹn giờ bật tắt m&aacute;yM&agrave;n h&igrave;nh hiển thị nhiệt độ tr&ecirc;n d&agrave;n lạnh&nbsp;Thổi gi&oacute; dễ chịu (cho trẻ em, người gi&agrave;)&nbsp;Tự khởi động lại khi c&oacute; điện&nbsp;Điều khiển bằng điện thoại, c&oacute; wifi&nbsp;Đảo gi&oacute; 4 chiều gi&uacute;p hơi lạnh lan toả đồng đều</td>
		</tr>
		<tr>
			<td>Ti&ecirc;u thụ điện:</td>
			<td>1.03 kW/h&nbsp;5 sao (Hiệu suất năng lượng 5.29)</td>
		</tr>
		<tr>
			<td>H&atilde;ng</td>
			<td>LG</td>
		</tr>
	</tbody>
</table>
')
INSERT [dbo].[products] ([product_id], [category_id], [brand_id], [product_name], [price], [create_date], [description], [image], [quantity], [active], [supplier_id], [configuration]) VALUES (19, 21, 4, N'Máy giặt Toshiba Inverter 9.0 kg AW-DK1000FV(KK)', 6490000, CAST(N'2023-10-02T00:00:00.000' AS DateTime), N'<h3>Sang trọng v&agrave; đẳng cấp</h3>

<p>C&oacute; thiết kế hiện đại với nắp m&aacute;y l&agrave;m từ k&iacute;nh cường lực kh&ocirc;ng tay nắm sang trọng c&ugrave;ng vỏ m&aacute;y bằng th&eacute;p mạ kẽm mang sắc đen huyền b&iacute;, tinh tế,&nbsp;m&aacute;y giặt Toshiba Inverter 14 kg AW-DUG1500WV KK&nbsp;sẽ l&agrave; điểm nhấn ho&agrave;n hảo, l&agrave;m h&agrave;i l&ograve;ng cả những vị kh&aacute;ch h&agrave;ng kh&oacute; t&iacute;nh nhất.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/189030/Slider/vi-vn-toshiba-aw-dug1500wv-kk-1.jpg"><img alt="Máy giặt Toshiba Inverter 14 kg AW-DUG1500WV KK" src="https://cdn.tgdd.vn/Products/Images/1944/189030/Slider/vi-vn-toshiba-aw-dug1500wv-kk-1.jpg" /></a></p>

<p>B&ecirc;n cạnh đ&oacute;, nếu gia đ&igrave;nh c&oacute; đ&ocirc;ng th&agrave;nh vi&ecirc;n hoặc c&oacute; th&oacute;i quen dồn nhiều quần &aacute;o để giặt một lần th&igrave; chiếc&nbsp;m&aacute;y giặt Toshiba 14 kg&nbsp;n&agrave;y ch&iacute;nh l&agrave; một sự lựa chọn đ&aacute;ng c&acirc;n nhắc.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/189030/Slider/vi-vn-toshiba-aw-dug1500wv-kk-2.jpg"><img alt="Khối lượng 14 kg - Máy giặt Toshiba Inverter 14 kg AW-DUG1500WV KK" src="https://cdn.tgdd.vn/Products/Images/1944/189030/Slider/vi-vn-toshiba-aw-dug1500wv-kk-2.jpg" /></a></p>

<h3>C&ocirc;ng nghệ Inverter&nbsp;tiết kiệm tối đa chi ph&iacute; điện, nước h&agrave;ng th&aacute;ng cho gia đ&igrave;nh</h3>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/189030/Slider/vi-vn-toshiba-aw-dug1500wv-kk-3.jpg"><img alt="Công nghệ Inverter - Máy giặt Toshiba Inverter 14 kg AW-DUG1500WV KK" src="https://cdn.tgdd.vn/Products/Images/1944/189030/Slider/vi-vn-toshiba-aw-dug1500wv-kk-3.jpg" /></a></p>

<h3>Độc đ&aacute;o với c&ocirc;ng nghệ&nbsp;si&ecirc;u bọt kh&iacute; NANO&nbsp;(UFB) đ&aacute;nh bay mọi vết bẩn cứng đầu nhất</h3>

<p>Khi sử dụng m&aacute;y giặt, những vết bẩn nh&igrave;n thấy bằng mắt thường sẽ được giặt sạch. Vậy c&ograve;n những vết bẩn ẩn s&acirc;u trong từng sợi vải th&igrave; sao?</p>

<p>Với c&ocirc;ng nghệ si&ecirc;u bọt kh&iacute; NANO (UFB) của&nbsp;m&aacute;y giặt Toshiba, chất giặt tẩy sẽ được đ&aacute;nh tan th&agrave;nh dạng bọt kh&iacute; si&ecirc;u nhỏ, len lỏi v&agrave; thẩm thấu s&acirc;u v&agrave;o từng sợi vải cũng như kẽ hở của ch&uacute;ng, cuốn bay những vết bẩn cứng đầu nhất, mang đến hiệu quả giặt tẩy đ&aacute;ng kinh ngạc.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/189030/may-giat-toshiba-aw-dug1500wv-kk-34.jpg"><img alt="Công nghệ giặt UFB" src="https://cdn.tgdd.vn/Products/Images/1944/189030/may-giat-toshiba-aw-dug1500wv-kk-34.jpg" /></a></p>

<h3>Thanh lăn k&eacute;p tăng cường chuyển động của đồ giặt, chống xoắn rối tối ưu cho quần &aacute;o</h3>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/189030/Slider/vi-vn-toshiba-aw-dug1500wv-kk-6.jpg"><img alt="Thanh lăn kép - Máy giặt Toshiba Inverter 14 kg AW-DUG1500WV KK" src="https://cdn.tgdd.vn/Products/Images/1944/189030/Slider/vi-vn-toshiba-aw-dug1500wv-kk-6.jpg" /></a></p>

<h3>M&acirc;m giặt&nbsp;Power&nbsp;tạo d&ograve;ng nước xo&aacute;y đa chiều mạnh mẽ</h3>

<p>Với thiết kế 3 c&aacute;nh đặc biệt, m&acirc;m giặt Mega Power Wash của m&aacute;y giặt Toshiba&nbsp;AW-DUG1500WV KK sẽ&nbsp;tạo ra d&ograve;ng nước xo&aacute;y đa chiều cực mạnh, nhanh ch&oacute;ng giặt sạch quần &aacute;o.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/189030/Slider/vi-vn-toshiba-aw-dug1500wv-kk-7.jpg"><img alt="Mâm giặt Mega Power Wash - Máy giặt Toshiba Inverter 14 kg AW-DUG1500WV KK" src="https://cdn.tgdd.vn/Products/Images/1944/189030/Slider/vi-vn-toshiba-aw-dug1500wv-kk-7.jpg" /></a></p>

<h3>Lồng giặt&nbsp;ng&ocirc;i sao pha l&ecirc;&nbsp;bảo vệ &aacute;o quần bền đẹp như mới với thời gian</h3>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/189030/Slider/vi-vn-toshiba-aw-dug1500wv-kk-9.jpg"><img alt="Lồng giặt ngôi sao pha lê - Máy giặt Toshiba Inverter 14 kg AW-DUG1500WV KK" src="https://cdn.tgdd.vn/Products/Images/1944/189030/Slider/vi-vn-toshiba-aw-dug1500wv-kk-9.jpg" /></a></p>

<h3>Gi&uacute;p quần &aacute;o tiếp x&uacute;c đều với bột giặt nhờ hiệu ứng th&aacute;c nước đ&ocirc;i hiện đại</h3>

<p>Ở chiếc&nbsp;<a href="https://www.dienmayxanh.com/may-giat" target="_blank">m&aacute;y giặt</a>&nbsp;n&agrave;y, d&ograve;ng nước mang x&agrave; ph&ograve;ng sẽ được m&acirc;m giặt đẩy l&ecirc;n tr&ecirc;n theo 2 đường ri&ecirc;ng biệt v&agrave; h&igrave;nh th&agrave;nh 2 d&ograve;ng th&aacute;c đổ mạnh xuống lồng giặt h&igrave;nh th&agrave;nh&nbsp;hiệu ứng th&aacute;c nước đ&ocirc;i, gi&uacute;p quần &aacute;o được tiếp x&uacute;c đều với bột giặt, đảm bảo cho quần &aacute;o được giặt sạch đồng đều, hiệu quả.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/189030/Slider/vi-vn-toshiba-aw-dug1500wv-kk-10.jpg"><img alt="Hiệu ứng thác nước đôi - Máy giặt Toshiba Inverter 14 kg AW-DUG1500WV KK" src="https://cdn.tgdd.vn/Products/Images/1944/189030/Slider/vi-vn-toshiba-aw-dug1500wv-kk-10.jpg" /></a></p>

<h3>Chế độ giặt lưu giữ hương thơm Fragrance Course cho quần &aacute;o thơm ng&aacute;t cả ng&agrave;y</h3>

<p>Nhờ t&iacute;nh năng&nbsp;Fragrance Course, m&aacute;y giặt sẽ tự động ng&acirc;m &aacute;o quần trong nước xả vải trong khoảng thời gian th&iacute;ch hợp. Nhờ vậy quần &aacute;o kh&ocirc;ng chỉ được mềm mại hơn m&agrave; c&ograve;n lưu giữ được hương thơm ng&aacute;t cả ng&agrave;y cho bạn.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/189030/Slider/vi-vn-toshiba-aw-dug1500wv-kk-11.jpg"><img alt="Tính năng lưu giữ hương thơm Fragrance Course - Máy giặt Toshiba Inverter 14 kg AW-DUG1500WV KK" src="https://cdn.tgdd.vn/Products/Images/1944/189030/Slider/vi-vn-toshiba-aw-dug1500wv-kk-11.jpg" /></a></p>

<h3>M&aacute;y giặt với nhiều tiện &iacute;ch vượt trội</h3>

<p><strong>Đ&egrave;n chiếu s&aacute;ng lồng giặt</strong>: Dễ d&agrave;ng quan s&aacute;t quần &aacute;o b&ecirc;n trong lồng giặt, kh&ocirc;ng lo bị s&oacute;t lại khi lấy quần &aacute;o ra.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/189030/Slider/vi-vn-toshiba-aw-dug1500wv-kk-8.jpg"><img alt="Đèn chiếu sáng lồng giặt - Máy giặt Toshiba Inverter 14 kg AW-DUG1500WV KK" src="https://cdn.tgdd.vn/Products/Images/1944/189030/Slider/vi-vn-toshiba-aw-dug1500wv-kk-8.jpg" /></a></p>

<p><strong>Tự động vệ sinh lồng giặt</strong>: Tiết kiệm tối đa thời gian v&agrave; chi ph&iacute; l&agrave;m vệ sinh. N&acirc;ng cao tuổi thọ v&agrave; chất lượng giặt tẩy cho m&aacute;y.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/189030/may-giat-toshiba-aw-dug1500wv-kk-6.jpg"><img alt="Tự động vệ sinh lồng giặt - Máy giặt Toshiba Inverter 14 kg AW-DUG1500WV KK" src="https://cdn.tgdd.vn/Products/Images/1944/189030/may-giat-toshiba-aw-dug1500wv-kk-6.jpg" /></a></p>
', N'maygiat3.png', 1, 1, 1, N'<table style="width:1099px">
	<tbody>
		<tr>
			<td>Loại m&aacute;y:</td>
			<td>Cửa tr&ecirc;nLồng đứng&nbsp;C&oacute; Inverter</td>
		</tr>
		<tr>
			<td>Khối lượng giặt:</td>
			<td>14 KgTr&ecirc;n 7 người</td>
		</tr>
		<tr>
			<td>Kiểu động cơ:</td>
			<td>Truyền động trực tiếp bền &amp; &ecirc;m</td>
		</tr>
		<tr>
			<td>C&ocirc;ng nghệ giặt:</td>
			<td>C&ocirc;ng nghệ UFB si&ecirc;u bọt kh&iacute; NANO&nbsp;Lồng giặt ng&ocirc;i sao pha l&ecirc;&nbsp;M&acirc;m giặt Mega Power WashThanh lăn k&eacute;p&nbsp;T&iacute;nh năng lưu giữ hương thơm Fragrance Course</td>
		</tr>
		<tr>
			<td>Tiện &iacute;ch:</td>
			<td>Hẹn giờ giặt&nbsp;Kh&oacute;a trẻ em&nbsp;Tự khởi động lại khi c&oacute; điện&nbsp;Vệ sinh lồng giặt&nbsp;Đ&egrave;n chiếu s&aacute;ng lồng giặt</td>
		</tr>
		<tr>
			<td>K&iacute;ch thước &ndash; Khối lượng:</td>
			<td>Cao 106.1 cm &ndash; Ngang 64 cm &ndash; S&acirc;u 66 cm &ndash; Nặng 53 kg</td>
		</tr>
		<tr>
			<td>Năm ra mắt</td>
			<td>2022</td>
		</tr>
		<tr>
			<td>H&atilde;ng</td>
			<td>Toshiba</td>
		</tr>
	</tbody>
</table>
')
INSERT [dbo].[products] ([product_id], [category_id], [brand_id], [product_name], [price], [create_date], [description], [image], [quantity], [active], [supplier_id], [configuration]) VALUES (20, 21, 4, N'Máy giặt LG AI DD Inverter 8.5 kg', 12490000, CAST(N'2023-10-02T00:00:00.000' AS DateTime), N'<h3>Gọn g&agrave;ng v&agrave; sang trọng</h3>

<p>M&aacute;y giặt LG AI DD Inverter 8.5 kg FV1408S4V&nbsp;được thiết kế tối ưu, giặt được nhiều quần &aacute;o hơn, trong khi k&iacute;ch thước m&aacute;y vẫn gọn g&agrave;ng. Thiết kế vu&ocirc;ng vắn với m&agrave;u x&aacute;m ghi sang trọng c&ograve;n g&oacute;p phần khiến căn nh&agrave; của bạn tr&ocirc;ng tinh tế v&agrave; hiện đại hơn.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/227122/Slider/141020-09311611.jpg"><img alt="Máy giặt LG Inverter 8.5 kg FV1408S4V - Thiết kế hiện đại" src="https://cdn.tgdd.vn/Products/Images/1944/227122/Slider/141020-09311611.jpg" /></a></p>

<p>Nắp m&aacute;y giặt được l&agrave;m bằng k&iacute;nh chịu lực, lồng giặt được l&agrave;m từ chất liệu th&eacute;p kh&ocirc;ng gỉ, gi&uacute;p gia tăng độ bền cho m&aacute;y giặt v&agrave; kh&aacute;ng khuẩn đến 99.9%.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/227122/Slider/141020-0931046.jpg"><img alt="Máy giặt LG Inverter 8.5 kg FV1408S4V - Thép không gỉ" src="https://cdn.tgdd.vn/Products/Images/1944/227122/Slider/141020-0931046.jpg" /></a></p>

<h3>14 chương tr&igrave;nh giặt tiện lợi, linh hoạt trong qu&aacute; tr&igrave;nh giặt</h3>

<p>Bảng điều khiển gồm n&uacute;t bật/tắt, n&uacute;t chọn chương tr&igrave;nh giặt, n&uacute;t khởi động tạm dừng ở b&ecirc;n tr&aacute;i v&agrave; m&agrave;n h&igrave;nh hiển thị đ&egrave;n b&aacute;o ở b&ecirc;n phải. C&oacute; 14 chương tr&igrave;nh giặt như: giặt nhanh 30 ph&uacute;t, giặt ngứa dị ứng, giặt hơi nước đồ trẻ em, giặt tay/đồ len, giặt &ecirc;m, giặt đồ trải giường, giặt nhẹ, vải b&ocirc;ng,&hellip; Trong đ&oacute;, chương tr&igrave;nh giặt nhanh 30 ph&uacute;t tiết kiệm thời gian giặt m&agrave; quần &aacute;o vẫn sạch sẽ, tinh tươm.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/227122/vi-vn-may-giat-lg-fv1408s4v200.png"><img alt="Máy giặt LG Inverter 8.5 kg FV1408S4V - 14 chương trình giặt" src="https://cdn.tgdd.vn/Products/Images/1944/227122/vi-vn-may-giat-lg-fv1408s4v200.png" /></a></p>

<h3>Hệ thống bảo vệ sợi bằng tr&iacute; tuệ nh&acirc;n tạo</h3>

<p>Bằng c&aacute;ch t&iacute;nh to&aacute;n khối lượng v&agrave; độ mềm của sợi vải, hệ thống tr&iacute; tuệ nh&acirc;n tạo AI DD sẽ chọn kiểu giặt, vắt ph&ugrave; hợp nhất cho mỗi lần giặt. Để bảo quản quần &aacute;o tốt hơn, kh&ocirc;ng bị nh&atilde;o hay mất d&aacute;ng sau khi giặt.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/227122/may-giat-lg-fv1408s4v-290921-120923.jpg"><img alt="Máy giặt LG Inverter 8.5 kg FV1408S4V - Cảm biến AI DD" src="https://cdn.tgdd.vn/Products/Images/1944/227122/may-giat-lg-fv1408s4v-290921-120923.jpg" /></a></p>

<h3>Giặt sạch s&acirc;u đồng thời giảm nếp nhăn tr&ecirc;n quần &aacute;o</h3>

<p>C&ocirc;ng nghệ&nbsp;giặt hơi nước&nbsp;LG Steam gi&uacute;p loại bỏ 99,9% t&aacute;c nh&acirc;n như mạt bụi, bụi si&ecirc;u nhỏ trong sợi vải g&acirc;y ra dị ứng hoặc c&aacute;c vấn đề về h&ocirc; hấp.&nbsp;M&aacute;y giặt LG&nbsp;FV1408S4V cũng gi&uacute;p giảm c&aacute;c nếp nhăn tr&ecirc;n quần &aacute;o đến 30%.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/227122/Slider/141020-0931088.jpg"><img alt="Máy giặt LG Inverter 8.5 kg FV1408S4V - Giặt hơi nước" src="https://cdn.tgdd.vn/Products/Images/1944/227122/Slider/141020-0931088.jpg" /></a></p>

<p><em>*&nbsp;Hiệp hội dị ứng Anh quốc Allergy UK l&agrave; một tổ chức từ thiện h&agrave;ng đầu về y tế d&agrave;nh cho người bị dị ứng, được th&agrave;nh lập v&agrave;o năm 1991 với t&ecirc;n gọi Quỹ Dị ứng của Anh (British Allergy Foundation &ndash; BAF). Đến năm 2002, tổ chức đổi t&ecirc;n th&agrave;nh Allergy UK.</em></p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/227122/may-giat-lg-fv1408s4v-271121-111010.jpg"><img alt="Kết quả chứng nhận thử nghiệm công nghệ giặt hơi nước Steam" src="https://cdn.tgdd.vn/Products/Images/1944/227122/may-giat-lg-fv1408s4v-271121-111010.jpg" /></a></p>

<p><em>C&ocirc;ng nghệ hơi nước Steam tr&ecirc;n m&aacute;y giặt LG được Cố vấn Khoa học về Dị ứng Vương quốc Anh c&ocirc;ng nhận c&oacute; thể loại bỏ 99,9% vi khuẩn g&acirc;y bệnh.</em></p>

<p>Ngo&agrave;i ra, c&ocirc;ng nghệ&nbsp;giặt 6 chuyển động, quần &aacute;o sẽ sạch sẽ như được giặt qua 6 bước giặt tay.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/227122/may-giat-lg-fv1408s4v-290921-121045.jpg"><img alt="Máy giặt LG Inverter 8.5 kg FV1408S4V | Giặt 6 chuyển động" src="https://cdn.tgdd.vn/Products/Images/1944/227122/may-giat-lg-fv1408s4v-290921-121045.jpg" /></a></p>

<h3>Khối lượng giặt 8.5 Kg&nbsp;ph&ugrave; hợp cho nh&agrave; từ 3 &ndash; 5 người</h3>

<p>M&aacute;y giặt LG c&oacute; khả năng giặt c&ugrave;ng l&uacute;c đến 8.5 Kg quần &aacute;o th&iacute;ch hợp cho c&aacute;c gia đ&igrave;nh c&oacute; từ 3 &ndash; 5 th&agrave;nh vi&ecirc;n.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/227122/Slider/141020-0930541.jpg"><img alt="Máy giặt LG Inverter 8.5 kg FV1408S4V - Khối lượng" src="https://cdn.tgdd.vn/Products/Images/1944/227122/Slider/141020-0930541.jpg" /></a></p>

<h3>Kh&ocirc;ng lo s&oacute;t quần &aacute;o với t&iacute;nh năng Add Item</h3>

<p><a href="https://www.dienmayxanh.com/may-giat" target="_blank">M&aacute;y giặt</a>&nbsp;c&ograve;n được trang bị t&iacute;nh năng&nbsp;th&ecirc;m đồ trong khi giặt&nbsp;cực k&igrave; tiện lợi, chỉ cần ấn n&uacute;t Add Item sau đ&oacute; mở cửa lồng v&agrave; th&ecirc;m những đồ bỏ s&oacute;t v&agrave;o v&agrave; tiếp tục giặt.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/227122/themdo.jpg"><img alt="Máy giặt LG Inverter 8.5 kg FV1408S4V - Thêm đồ tiện lợi" src="https://cdn.tgdd.vn/Products/Images/1944/227122/themdo.jpg" /></a></p>

<h3>Tiết kiệm tối ưu cho gia đ&igrave;nh bạn</h3>

<p>Với c&ocirc;ng nghệ Inverter, mẫu m&aacute;y giặt&nbsp;LG tiết kiệm đ&aacute;ng kể mức ti&ecirc;u thụ điện v&agrave; nước. H&oacute;a đơn mỗi th&aacute;ng trong gia đ&igrave;nh bạn sẽ &ldquo;nhẹ g&aacute;nh&rdquo; hơn.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/227122/may-giat-lg-fv1408s4v-182120-032150.jpg"><img alt="Máy giặt LG Inverter 8.5 kg FV1408S4V | Tiết kiệm" src="https://cdn.tgdd.vn/Products/Images/1944/227122/may-giat-lg-fv1408s4v-182120-032150.jpg" /></a></p>

<h3>Tăng k&iacute;ch thước lồng giặt nhưng vẫn giữ nguy&ecirc;n diện t&iacute;ch m&aacute;y giặt</h3>

<p>Tăng k&iacute;ch thước lồng giặt gi&uacute;p giặt nhiều quần &aacute;o hơn so với c&aacute;c sản phẩm c&ugrave;ng k&iacute;ch thước m&agrave; vẫn giữ nguy&ecirc;n diện t&iacute;ch m&aacute;y giặt.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/227122/Slider/141020-0931025.jpg"><img alt="Máy giặt LG Inverter 8.5 kg FV1408S4V - Tăng kích thước lồng giặt" src="https://cdn.tgdd.vn/Products/Images/1944/227122/Slider/141020-0931025.jpg" /></a></p>

<h3>Điều khiển từ xa dễ d&agrave;ng bằng smartphone</h3>

<p>Bạn c&oacute; thể kết nối m&aacute;y giặt với ứng dụng Smart ThinQ để ra lệnh cho m&aacute;y bắt đầu giặt từ xa, hẹn giờ giặt, chọn chương tr&igrave;nh giặt. Giao diện dễ sử dụng.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/227122/Slider/141020-09311310.jpg"><img alt="Máy giặt LG FV1408S4V - Smart ThinQ - Ứng dụng Smart ThinQ" src="https://cdn.tgdd.vn/Products/Images/1944/227122/Slider/141020-09311310.jpg" /></a></p>

<p>Với những t&iacute;nh năng v&agrave; c&ocirc;ng nghệ như:&nbsp;C&ocirc;ng nghệ giặt hơi nước Steam, c&ocirc;ng nghệ 6 chuyển động DD kết hợp tr&iacute; th&ocirc;ng minh nh&acirc;n tạo AI,&nbsp;c&ocirc;ng nghệ Inverter v&agrave; điều khiển từ xa qua&nbsp;SmartThinQ th&igrave; chiếc&nbsp;m&aacute;y giặt LG FV1408S4V sẽ l&agrave; một lựa chọn l&yacute; tưởng cho những gia đ&igrave;nh c&oacute; 3 &ndash; 5 người.</p>
', N'maygiat1.png', 12, 1, 1, N'<table class="table" style="width:1099px">
	<tbody>
		<tr>
			<td>Loại m&aacute;y:</td>
			<td>Cửa trước&nbsp;Lồng ngang&nbsp;C&oacute; Inverter</td>
		</tr>
		<tr>
			<td>Khối lượng giặt:</td>
			<td>8.5 Kg&nbsp;Từ 3 &ndash; 5 người</td>
		</tr>
		<tr>
			<td>Kiểu động cơ:</td>
			<td>Truyền động trực tiếp &ndash; sử dụng tr&iacute; tuệ nh&acirc;n tạo</td>
		</tr>
		<tr>
			<td>C&ocirc;ng nghệ giặt:</td>
			<td>C&ocirc;ng nghệ AI DD bảo vệ sợi vảiC&ocirc;ng nghệ giặt 6 motion DDC&ocirc;ng nghệ giặt hơi nước Steam (cửa trước)</td>
		</tr>
		<tr>
			<td>Tiện &iacute;ch:</td>
			<td>Cho ph&eacute;p điều khiển m&aacute;y giặt từ xa qua ứng dụng SmartThinQ&nbsp;Hẹn giờ giặtKh&oacute;a trẻ em&nbsp;Th&ecirc;m đồ trong khi giặt&nbsp;Vệ sinh lồng giặt</td>
		</tr>
		<tr>
			<td>K&iacute;ch thước &ndash; Khối lượng:</td>
			<td>Cao 85 cm &ndash; Ngang 60 cm &ndash; S&acirc;u 61 cm &ndash; Nặng 62 kg</td>
		</tr>
		<tr>
			<td>Năm ra mắt</td>
			<td>2022</td>
		</tr>
		<tr>
			<td>H&atilde;ng</td>
			<td>LG</td>
		</tr>
	</tbody>
</table>
')
INSERT [dbo].[products] ([product_id], [category_id], [brand_id], [product_name], [price], [create_date], [description], [image], [quantity], [active], [supplier_id], [configuration]) VALUES (42, 21, 4, N'Máy giặt LG AI DD Inverter 15 Kg F2515STGW', 1200000, CAST(N'2023-10-02T00:00:00.000' AS DateTime), N'<h3>N&acirc;ng cao hiệu quả giặt sạch, vận h&agrave;nh &ecirc;m &aacute;i nhờ c&ocirc;ng nghệ AI DD</h3>

<p>M&aacute;y giặt LG AI DD Inverter 15 Kg F2515STGW&nbsp;được trang bị c&ocirc;ng nghệ AI DD (l&agrave; sự kết hợp giữa động cơ dẫn động trực tiếp Intello DD v&agrave; tr&iacute; th&ocirc;ng minh nh&acirc;n tạo AI) c&oacute; khả năng nhận biết khối lượng v&agrave; độ mềm của quần &aacute;o, từ đ&oacute; lựa chọn chuyển động ph&ugrave; hợp để n&acirc;ng cao hiệu quả giặt sạch.</p>

<p>Đồng thời, c&ocirc;ng nghệ AI DD cũng gi&uacute;p&nbsp;m&aacute;y giặt&nbsp;vận h&agrave;nh &ecirc;m &aacute;i v&agrave; giảm thiểu tiếng ồn đ&aacute;ng kể khi hoạt động.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/238291/Slider/lg-f2515stgw-100521-0137151.jpg"><img alt="Máy giặt LG Inverter 15 Kg F2515STGW - Công nghệ AI DD vận hành êm, tối ưu chương trình giặt" src="https://cdn.tgdd.vn/Products/Images/1944/238291/Slider/lg-f2515stgw-100521-0137151.jpg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<h3>Bảo vệ sợi vải tối ưu, giảm xoắn rối quần &aacute;o với&nbsp;c&ocirc;ng nghệ giặt 6 chuyển động</h3>

<p>C&ocirc;ng nghệ giặt 6 chuyển động được m&ocirc; phỏng như b&agrave;n tay để thực hiện c&aacute;c thao t&aacute;c như: đập, nh&agrave;o trộn, n&eacute;n, đảo, quay v&agrave; ch&agrave; x&aacute;t, gi&uacute;p vết bẩn tr&ecirc;n quần &aacute;o được loại bỏ dễ d&agrave;ng. Hơn nữa, c&ocirc;ng nghệ n&agrave;y c&ograve;n g&oacute;p phần bảo vệ sợi vải tối ưu v&agrave; giảm t&igrave;nh trạng xoắn rối sau khi giặt.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/238291/lg-f2515stgw-280921-114933.jpg"><img alt="Máy giặt LG Inverter 15 Kg F2515STGW- Bảo vệ sợi vải tối ưu, giảm xoắn rối quần áo với công nghệ giặt 6 chuyển động" src="https://cdn.tgdd.vn/Products/Images/1944/238291/lg-f2515stgw-280921-114933.jpg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<h3>Diệt khuẩn, ph&ograve;ng ngừa dị ứng với&nbsp;c&ocirc;ng nghệ giặt hơi nước Steam+</h3>

<p>Mẫu&nbsp;m&aacute;y giặt LG lồng ngang&nbsp;c&oacute; c&ocirc;ng nghệ Steam+ được t&iacute;ch hợp trong 2 chế độ l&agrave;: Baby Steam Care v&agrave; Allergy Care. Ở chế độ Baby Steam Care sẽ sử dụng&nbsp;nhiệt độ của hơi nước để xử l&yacute; c&aacute;c vết bẩn ngay từ đầu chu tr&igrave;nh giặt.</p>

<p>C&ograve;n với chế độ Allergy Care, hơi nước n&oacute;ng c&ograve;n mang lại hiệu quả diệt khuẩn v&agrave; loại bỏ c&aacute;c t&aacute;c nh&acirc;n g&acirc;y dị ứng b&aacute;m tr&ecirc;n đồ giặt, nhờ đ&oacute; bảo vệ sức khỏe l&agrave;n da người mặc tốt hơn.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/238291/lg-f2515stgw-121021-094141.jpg"><img alt="Máy giặt LG Inverter 15 Kg F2515STGW-Diệt khuẩn, phòng ngừa dị ứng với công nghệ giặt hơi nước Steam+" src="https://cdn.tgdd.vn/Products/Images/1944/238291/lg-f2515stgw-121021-094141.jpg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<p><em>* Hiệp hội dị ứng Anh quốc Allergy UK l&agrave; một tổ chức từ thiện h&agrave;ng đầu về y tế d&agrave;nh cho người bị dị ứng, được th&agrave;nh lập v&agrave;o năm 1991 với t&ecirc;n gọi Quỹ Dị ứng của Anh (British Allergy Foundation &ndash; BAF). Đến năm 2002, tổ chức đổi t&ecirc;n th&agrave;nh Allergy UK.</em></p>

<p><img alt="Kết quả chứng nhận thử nghiệm công nghệ giặt hơi nước Steam+" src="https://cdn.tgdd.vn/Products/Images/1944/238291/lg-f2515stgw-251121-110831.jpg" /></p>

<p><em>C&ocirc;ng nghệ hơi nước Steam+ tr&ecirc;n m&aacute;y giặt LG được Cố vấn Khoa học về Dị ứng Vương quốc Anh c&ocirc;ng nhận c&oacute; thể loại bỏ 99,9% vi khuẩn g&acirc;y bệnh.</em></p>

<h3>Giặt sạch v&agrave; nhanh hơn với chế độ TurboWash</h3>

<p>Với&nbsp;chế độ TurboWash, quần &aacute;o sẽ được giặt sạch,&nbsp;r&uacute;t ngắn bớt thời gian ti&ecirc;u tốn cho việc giặt giũ m&agrave; kh&ocirc;ng l&agrave;m mất đi t&iacute;nh hiệu quả nhờ lực phun tia nước mạnh, l&agrave;m tăng mức độ thẩm thấu chất giặt tẩy v&agrave;o s&acirc;u b&ecirc;n trong sợi vải, đồng thời lồng giặt sẽ quay tốc độ nhanh hơn gi&uacute;p loại bỏ vết bẩn hiệu quả.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/238291/lg-f2515stgw-280921-115756.jpg"><img alt="Máy giặt LG Inverter 15 Kg F2515STGW - Chế độ TurboWash tiết kiệm thời gian, giặt sạch hiệu quả" src="https://cdn.tgdd.vn/Products/Images/1944/238291/lg-f2515stgw-280921-115756.jpg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<h3>Tiết kiệm điện, nước nhờ&nbsp;động cơ truyền động trực tiếp, bảo h&agrave;nh đến 10 năm</h3>

<p>LG Inverter F2515STGW sử dụng động cơ truyền động trực tiếp n&ecirc;n mang lại hiệu quả tiết kiệm điện v&agrave; nước tối ưu. Kh&ocirc;ng những thế, động cơ c&ograve;n được nh&agrave; sản xuất bảo h&agrave;nh đến tận 10 năm, gi&uacute;p bạn an t&acirc;m v&agrave; thoải m&aacute;i sử dụng&nbsp;m&aacute;y giặt LG&nbsp;trong suốt thời gian d&agrave;i.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/238291/Slider/lg-f2515stgw-100521-1226030.jpg"><img alt="Máy giặt LG Inverter 15 Kg F2515STGW - Tiết kiệm điện, nước nhờ động cơ truyền động trực tiếp, bảo hành đến 10 năm" src="https://cdn.tgdd.vn/Products/Images/1944/238291/Slider/lg-f2515stgw-100521-1226030.jpg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<h3>Điều khiển từ xa nhanh ch&oacute;ng, chẩn đo&aacute;n lỗi th&ocirc;ng minh với&nbsp;ứng dụng Smart ThinQ</h3>

<p>Khi c&agrave;i đặt ứng dụng Smart ThinQ tr&ecirc;n&nbsp;điện thoại, bạn c&oacute; thể nhanh ch&oacute;ng điều khiển&nbsp;m&aacute;y giặt LG Inverter từ xa cũng như cập nhật c&aacute;c chương tr&igrave;nh giặt mới một c&aacute;ch tiện lợi v&agrave; chẩn đo&aacute;n lỗi th&ocirc;ng minh ngay lập tức để kh&ocirc;ng l&agrave;m ảnh hưởng đến thời gian giặt giũ của bạn.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/238291/lg-f2515stgw-280921-114940.jpg"><img alt="Máy giặt LG Inverter 15 Kg F2515STGW - Smart Thinq điều khiển từ xa, chuẩn đoán lỗi" src="https://cdn.tgdd.vn/Products/Images/1944/238291/lg-f2515stgw-280921-114940.jpg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<h3>Khối lượng giặt 15kg, ph&ugrave; hợp gia đ&igrave;nh đ&ocirc;ng người tr&ecirc;n 7 th&agrave;nh vi&ecirc;n</h3>

<p>M&aacute;y giặt LG Inverter F2515STGW sở hữu khối lượng giặt 15kg, đ&aacute;p ứng nhu cầu giặt giũ của những hộ gia đ&igrave;nh đ&ocirc;ng th&agrave;nh vi&ecirc;n (tr&ecirc;n 7 người), hoặc những gia đ&igrave;nh &iacute;t th&agrave;nh vi&ecirc;n hơn nhưng lại c&oacute; nhu cầu l&agrave;m sạch nhiều quần &aacute;o trong mỗi lần giặt.</p>

<p><img alt="Máy giặt LG Inverter 15 Kg F2515STGW-Khối lượng giặt 15kg, phù hợp gia đình đông người (trên 7 thành viên)" src="https://cdn.tgdd.vn/Products/Images/1944/238291/Slider/lg-f2515stgw-100521-1222335.jpg" /></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<h3>M&agrave;n h&igrave;nh Led hiển thị r&otilde;, bảng điều khiển song ngữ tiện sử dụng</h3>

<p>Thuộc kiểu&nbsp;<a href="https://www.dienmayxanh.com/may-giat?g=cua-truoc" target="_blank">m&aacute;y giặt lồng ngang</a>, LG Inverter F2515STGW được trang bị m&agrave;n h&igrave;nh Led hiển thị r&otilde; r&agrave;ng với bảng điều khiển thiết kế song ngữ Anh &ndash; Việt k&egrave;m với n&uacute;t xoay vặn tiện lợi, dễ d&agrave;ng thao t&aacute;c cho những ai mới lần đầu sử dụng.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/238291/Slider/lg-f2515stgw-100521-1222334.jpg"><img alt="Máy giặt LG Inverter 15 Kg F2515STGW-Màn hình Led hiển thị rõ, bảng điều khiển song ngữ tiện sử dụng" src="https://cdn.tgdd.vn/Products/Images/1944/238291/Slider/lg-f2515stgw-100521-1222334.jpg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<h3>12 chương tr&igrave;nh giặt được t&iacute;ch hợp sẵn, đ&aacute;p ứng tốt nhu cầu giặt giũ của của gia đ&igrave;nh</h3>

<p>M&aacute;y giặt LG Inverter 15 Kg F2515STGW đ&aacute;p ứng nhu cầu giặt cơ bản của gia đ&igrave;nh bạn với 12 chương tr&igrave;nh giặt (<a href="https://www.dienmayxanh.com/may-giat/lg-f2515stgw#top-tskt" target="_blank">xem chi tiết tại bảng th&ocirc;ng số kỹ thuật</a>): Giặt ngừa dị ứng, giặt vệ sinh, giặt đồ tinh xảo, giặt nhanh, đồ trẻ sơ sinh, vải b&ocirc;ng, vết bẩn kh&oacute; giặt, chăn l&ocirc;ng vũ v&agrave; 4 chương tr&igrave;nh giặt kh&aacute;c. Trong đ&oacute;, với chương tr&igrave;nh ngừa dị ứng, quần &aacute;o được giặt ở nhiệt độ cao, tốc độ quay vắt mạnh, gi&uacute;p ti&ecirc;u diệt một số chất g&acirc;y dị ứng, ph&ugrave; hợp cho những người c&oacute; l&agrave;n da nhạy cảm.</p>

<p><a href="https://cdn.tgdd.vn/Products/Images/1944/238291/lg-f2515stgw-251021-101749.jpg"><img alt="Máy giặt LG Inverter 15 Kg F2515STGW - 12 chương trình giặt tiện ích" src="https://cdn.tgdd.vn/Products/Images/1944/238291/lg-f2515stgw-251021-101749.jpg" /></a></p>

<p><em>*H&igrave;nh ảnh chỉ mang t&iacute;nh chất minh họa</em></p>

<p>T&oacute;m lại, LG Inverter 15 Kg F2515STGW l&agrave; chiếc m&aacute;y giặt ph&ugrave; hợp cho những gia đ&igrave;nh đ&ocirc;ng th&agrave;nh vi&ecirc;n (tr&ecirc;n 7 người). M&aacute;y c&oacute; khả năng giặt sạch tối ưu m&agrave; vẫn mang lại hiệu quả tiết kiệm điện, nước v&agrave; vận h&agrave;nh &ecirc;m &aacute;i, kh&ocirc;ng l&agrave;m ảnh hưởng đến kh&ocirc;ng gian sinh hoạt y&ecirc;n tĩnh của gia đ&igrave;nh. Hơn nữa, chiếc m&aacute;y giặt c&ograve;n ph&ugrave; hợp cho gia đ&igrave;nh c&oacute; trẻ nhỏ hoặc những ai c&oacute; l&agrave;n da nhạy cảm nhờ c&ocirc;ng nghệ giặt hơi nước.</p>
', N'maygiat2.png', 1, 1, 1, N'<table style="width:1099px">
	<tbody>
		<tr>
			<td>Loại m&aacute;y:</td>
			<td>Cửa trước&nbsp;Lồng ngang&nbsp;C&oacute; Inverter</td>
		</tr>
		<tr>
			<td>Khối lượng giặt:</td>
			<td>15 KgTr&ecirc;n 7 người</td>
		</tr>
		<tr>
			<td>Kiểu động cơ:</td>
			<td>Truyền động trực tiếp &ndash; sử dụng tr&iacute; tuệ nh&acirc;n tạo</td>
		</tr>
		<tr>
			<td>C&ocirc;ng nghệ giặt:</td>
			<td>C&ocirc;ng nghệ AI DD bảo vệ sợi vải&nbsp;C&ocirc;ng nghệ giặt 6 motion DDC&ocirc;ng nghệ giặt hơi nước Steam+C&ocirc;ng nghệ giặt tiết kiệm TurboWashCảm biến tự động x&aacute;c định chất liệu &aacute;o quần</td>
		</tr>
		<tr>
			<td>Tiện &iacute;ch:</td>
			<td>Cho ph&eacute;p điều khiển m&aacute;y giặt từ xa qua ứng dụng SmartThinQChức năng chẩn đo&aacute;n th&ocirc;ng minhHẹn giờ giặtKh&oacute;a trẻ emTh&ecirc;m đồ trong khi giặtVệ sinh lồng giặt</td>
		</tr>
		<tr>
			<td>K&iacute;ch thước &ndash; Khối lượng:</td>
			<td>Cao 94 cm &ndash; Ngang 63.2 cm &ndash; S&acirc;u 74.4 cm &ndash; Nặng 77 kg</td>
		</tr>
		<tr>
			<td>Năm ra mắt</td>
			<td>2022</td>
		</tr>
		<tr>
			<td>H&atilde;ng</td>
			<td>LG</td>
		</tr>
	</tbody>
</table>

<p><img src="chrome-extension://bpggmmljdiliancllaapiggllnkbjocb/logo/48.png" /></p>
')
INSERT [dbo].[products] ([product_id], [category_id], [brand_id], [product_name], [price], [create_date], [description], [image], [quantity], [active], [supplier_id], [configuration]) VALUES (46, 4, 1, N'Cặp loa karaoke JBL KI510 700W', 18198000, CAST(N'2023-10-02T00:00:00.000' AS DateTime), N'<h3>Loa 2 k&ecirc;nh 3 đường tiếng, chất lượng &acirc;m mạnh mẽ, trong trẻo, mượt m&agrave;</h3>

<p>Cặp Loa Karaoke JBL KI510&nbsp;tổng c&ocirc;ng suất 700W, c&ocirc;ng suất mạnh mẽ&nbsp;350W&nbsp;cho 1 k&ecirc;nh, dải tần rộng&nbsp;69 Hz &ndash; 16 kHz, loa JBL n&agrave;y truyền tải chất &acirc;m mạnh mẽ, sống động, độ phủ rộng.</p>

<p>Loa JBL&nbsp;3 đường tiếng thể hiện xuất sắc &acirc;m thanh ở mọi cường độ, cho bạn thưởng thức chất lượng &acirc;m tốt ở mọi thể loại nhạc:</p>

<p>&ndash; 2 loa Bass đường k&iacute;nh 25 cm cho &acirc;m thanh rền, ấm, kh&ocirc;ng bị r&egrave;.</p>

<p>&ndash; 2 loa Treble 2.5 cm dạng v&ograve;m với m&agrave;ng loa nh&ocirc;m sẽ cho tiếng treble r&otilde; s&aacute;ng, gi&uacute;p giọng ca th&ecirc;m phần r&agrave;nh mạch, trong trẻo.</p>

<p>&ndash; 2 loa Mid 12.5 cm.</p>

<p>C&ugrave;ng với c&ocirc;ng nghệ &acirc;m thanh Stereo, chất &acirc;m được t&aacute;i tạo ch&acirc;n thực, sống động, mượt m&agrave; v&agrave; l&ocirc;i cuốn, để bạn trải nghiệm &acirc;m thanh gần với thực tế nhất.</p>

<p><img alt="Cặp Loa Karaoke JBL KI510 - Công suất" src="https://cdn.tgdd.vn/Products/Images/2162/232349/cap-loa-karaoke-jbl-ki510-140421-0900030.jpg" /></p>

<h3>Thiết kế hiện đại, sang trọng, gọn g&agrave;ng v&agrave; tinh tế cho nhiều kh&ocirc;ng gian</h3>

<p>Loa&nbsp;với k&iacute;ch thước ngang x s&acirc;u x cao lần lượt 51 x 34.2 x 31 cm, khối lượng 12.4 kg/loa nhỏ gọn để c&oacute; thể lắp đặt ở nhiều vị tr&iacute; như tr&ecirc;n kệ tủ, treo tường&hellip;</p>

<p>Thiết kế bắt mắt với th&ugrave;ng loa chất liệu&nbsp;gỗ MDF d&agrave;y 15 mm bọc Vinyl với tone m&agrave;u n&acirc;u đen hiện đại, chống trầy xước, chống thấm nước.</p>

<p>Mặt trước chia l&agrave;m 2 phần r&otilde; rệt ph&acirc;n c&aacute;ch bằng dải kim loại với logo JBL nổi bật: Phần loa bass phủ lớp&nbsp;vải paillette, c&ograve;n phần loa mid v&agrave; treble l&agrave; lưới kim loại để &acirc;m thanh được khắc họa đặc trưng, nổi bật, r&otilde; n&eacute;t theo đặc t&iacute;nh của từng loại loa.</p>

<p>Logo JBL c&oacute; thể ph&aacute;t s&aacute;ng khi loa hoạt động, th&ecirc;m điểm nhấn, th&ecirc;m sức h&uacute;t v&agrave; sự sinh động cho kh&ocirc;ng gian giải tr&iacute; &acirc;m nhạc.</p>

<p><img alt="Thiết kế sang trọng - Cặp Loa Karaoke JBL KI510" src="https://cdn.tgdd.vn/Products/Images/2162/232349/cap-loa-karaoke-jbl-ki510-181820-051850.jpg" /></p>

<p>Lỗ tho&aacute;t hơi b&ecirc;n h&ocirc;ng th&ugrave;ng loa tr&aacute;nh &acirc;m thanh bị &ugrave; hay r&egrave; ngay cả khi loa đặt &aacute;p tường, tiện lợi v&agrave; dễ d&agrave;ng hơn khi chọn vị tr&iacute; bố tr&iacute; loa.</p>

<p><img alt="Dễ lắp đặt - Cặp Loa Karaoke JBL KI510" src="https://cdn.tgdd.vn/Products/Images/2162/232349/cap-loa-karaoke-jbl-ki510-294320-024359.jpg" /></p>

<p>Gợi &yacute; sử dụng&nbsp;loa Karaoke&nbsp;cho diện t&iacute;ch ph&ograve;ng 15 &ndash; 20 m&sup2; để c&oacute; được độ phủ &acirc;m tốt nhất.</p>

<h3>Thưởng thức đa dạng thể loại &acirc;m nhạc</h3>

<p>Với khả năng xử l&yacute; &acirc;m thanh uyển chuyển, đa dạng&nbsp;loa Karaoke JBL&nbsp;c&oacute; thể chơi được nhiều thể loại từ EDM mạnh mẽ s&ocirc;i động cho tới những b&agrave;i h&aacute;t Bolero trữ t&igrave;nh s&acirc;u lắng, thỏa m&atilde;n sở th&iacute;ch &acirc;m nhạc của mọi th&agrave;nh vi&ecirc;n trong gia đ&igrave;nh bạn.</p>

<p><img alt="Thưởng thức tốt mọi thể loại nhạc - Cặp Loa Karaoke JBL KI510" src="https://cdn.tgdd.vn/Products/Images/2162/232349/cap-loa-karaoke-jbl-ki510-250120-030132.jpg" /></p>

<p>Mặt sau th&ugrave;ng loa c&ograve;n c&oacute; c&ocirc;ng tắc tắt/mở đ&egrave;n logo, người d&ugrave;ng t&ugrave;y chỉnh để tăng sinh động cho kh&ocirc;ng gian &acirc;m nhạc với hiệu ứng ph&aacute;t s&aacute;ng từ cụm logo tr&ecirc;n th&ugrave;ng loa.</p>

<p><img alt="Công tắc tắt/mở đèn logo - Cặp Loa Karaoke JBL KI510" src="https://cdn.tgdd.vn/Products/Images/2162/232349/cap-loa-karaoke-jbl-ki510-293520-033543.jpg" /></p>

<h3>Đề xuất gh&eacute;p nối d&agrave;n karaoke ti&ecirc;u chuẩn:</h3>

<p>&ndash;&nbsp;Cặp Loa Karaoke JBL KI510.</p>

<p>&ndash; Amply c&oacute; c&ocirc;ng suất bằng hoặc tr&ecirc;n 700W.</p>

<p>&ndash; 2 loa sub c&oacute; c&ocirc;ng suất nhỏ hơn hoặc bằng c&ocirc;ng suất của Amply để đạt được chất lượng &acirc;m thanh tốt nhất.</p>

<p>&ndash; 2 Micro c&oacute; d&acirc;y hoặc kh&ocirc;ng d&acirc;y.</p>

<p>T&oacute;m lại, cặp loa JBL KI510 với chất lượng từ h&atilde;ng &acirc;m thanh h&agrave;ng đầu JBL hứa hẹn mang đến cho bạn sản phẩm kh&ocirc;ng chỉ tốt về &acirc;m thanh thể hiện m&agrave; c&ograve;n bền bỉ sử dụng theo thời gian, đ&aacute;ng để chi ti&ecirc;u, trải nghiệm.</p>
', N'loa1.png', 12, 1, 1, N'<table style="width:1099px">
	<tbody>
		<tr>
			<td>Tổng c&ocirc;ng suất:</td>
			<td>700W</td>
		</tr>
		<tr>
			<td>Nguồn:</td>
			<td>Cắm điện d&ugrave;ng</td>
		</tr>
		<tr>
			<td>Số lượng k&ecirc;nh:</td>
			<td>2.0 k&ecirc;nh</td>
		</tr>
		<tr>
			<td>Kết nối kh&aacute;c:</td>
			<td>Cổng kết nối với AmplyLine Level/LFE RCA Jack</td>
		</tr>
		<tr>
			<td>Tiện &iacute;ch:</td>
			<td>Bật/tắt đ&egrave;n logoC&oacute; thể h&aacute;t Karaoke</td>
		</tr>
		<tr>
			<td>C&ocirc;ng nghệ</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>Thương hiệu của:</td>
			<td>Mỹ</td>
		</tr>
		<tr>
			<td>H&atilde;ng</td>
			<td>JBL</td>
		</tr>
	</tbody>
</table>
')
SET IDENTITY_INSERT [dbo].[products] OFF
GO
INSERT [dbo].[suppliers] ([supplier_id], [supplier_name], [address], [phone], [email], [website], [describe]) VALUES (1, N'Panasnic	', N'HCM', N'0123456789', N'panasonic@gmail.com', N'panasonic.vn', N'ko')
GO
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([user_id], [Username], [Password], [Email], [FullName], [DateOfBirth], [Gender], [Address], [PhoneNumber], [LoginPermission], [RegistrationDate], [LockStatus], [Role]) VALUES (1, N'user', N'$2a$12$ZiJ7v4jOoxkTGDU2UOWLvuFMNGkWFSqeeZ45hBn08xQQ0UJmwZcR6', N'user@gmail.com', N'user', CAST(N'2002-01-01' AS Date), 1, N'123', N'0987890987', 1, CAST(N'2022-01-01T00:00:00.000' AS DateTime), 0, N'USER')
INSERT [dbo].[Users] ([user_id], [Username], [Password], [Email], [FullName], [DateOfBirth], [Gender], [Address], [PhoneNumber], [LoginPermission], [RegistrationDate], [LockStatus], [Role]) VALUES (2, N'admin', N'$2a$12$ZiJ7v4jOoxkTGDU2UOWLvuFMNGkWFSqeeZ45hBn08xQQ0UJmwZcR6', N'admin@gmail.com', N'admin', CAST(N'3333-03-03' AS Date), 1, N'123', N'0989878675', 1, CAST(N'2002-01-01T00:00:00.000' AS DateTime), 0, N'ADMIN')
INSERT [dbo].[Users] ([user_id], [Username], [Password], [Email], [FullName], [DateOfBirth], [Gender], [Address], [PhoneNumber], [LoginPermission], [RegistrationDate], [LockStatus], [Role]) VALUES (3, N'viennvpc04385@fpt.edu.vn', N'oauth2_dummy_password', N'viennvpc04385@fpt.edu.vn', N'Nguyen Van Vien (FPL CT K17)', CAST(N'2023-09-25' AS Date), NULL, NULL, NULL, 1, CAST(N'2023-09-25T15:22:00.670' AS DateTime), 0, N'USER')
INSERT [dbo].[Users] ([user_id], [Username], [Password], [Email], [FullName], [DateOfBirth], [Gender], [Address], [PhoneNumber], [LoginPermission], [RegistrationDate], [LockStatus], [Role]) VALUES (2005, N'Luanmvpc02891', N'$2a$10$WAOQNxtL2PZSYj1YYgUpTuGzSR4RhdqkEikC5jZvNcTqB4FP7XCJ2', N'luak@349gmail.com', N'Mai Vũ Luân', CAST(N'2005-09-01' AS Date), 1, NULL, N'0702807907', 0, NULL, 0, N'USER')
INSERT [dbo].[Users] ([user_id], [Username], [Password], [Email], [FullName], [DateOfBirth], [Gender], [Address], [PhoneNumber], [LoginPermission], [RegistrationDate], [LockStatus], [Role]) VALUES (2006, N'luak349@gmail.com', N'oauth2_dummy_password', N'luak349@gmail.com', N'luak mai', CAST(N'2023-09-26' AS Date), 1, N'sdgsdf', N'0702807933', 1, CAST(N'2023-09-26T13:45:38.603' AS DateTime), 0, N'USER')
INSERT [dbo].[Users] ([user_id], [Username], [Password], [Email], [FullName], [DateOfBirth], [Gender], [Address], [PhoneNumber], [LoginPermission], [RegistrationDate], [LockStatus], [Role]) VALUES (3007, N'luanmvpc02891@gmail.com', N'oauth2_dummy_password', N'luanmvpc02891@gmail.com', N'Luân Mai Vũ', CAST(N'2023-09-28' AS Date), NULL, NULL, NULL, 1, CAST(N'2023-09-28T17:03:13.757' AS DateTime), 0, N'USER')
INSERT [dbo].[Users] ([user_id], [Username], [Password], [Email], [FullName], [DateOfBirth], [Gender], [Address], [PhoneNumber], [LoginPermission], [RegistrationDate], [LockStatus], [Role]) VALUES (4007, N'luanmvpc02891@fpt.edu.vn', N'oauth2_dummy_password', N'luanmvpc02891@fpt.edu.vn', N'Mai Vu Luan (FPL CT)', CAST(N'2023-09-30' AS Date), NULL, NULL, NULL, 1, CAST(N'2023-09-30T18:09:09.983' AS DateTime), 0, N'USER')
INSERT [dbo].[Users] ([user_id], [Username], [Password], [Email], [FullName], [DateOfBirth], [Gender], [Address], [PhoneNumber], [LoginPermission], [RegistrationDate], [LockStatus], [Role]) VALUES (4008, N'vuluan01248@gmail.com', N'oauth2_dummy_password', N'vuluan01248@gmail.com', N'Vu Luan Mai', CAST(N'2023-10-02' AS Date), NULL, NULL, NULL, 1, CAST(N'2023-10-02T14:05:08.593' AS DateTime), 0, N'USER')
SET IDENTITY_INSERT [dbo].[Users] OFF
GO
ALTER TABLE [dbo].[cart_details]  WITH CHECK ADD  CONSTRAINT [FK_cart_details_carts] FOREIGN KEY([cart_id])
REFERENCES [dbo].[carts] ([card_id])
GO
ALTER TABLE [dbo].[cart_details] CHECK CONSTRAINT [FK_cart_details_carts]
GO
ALTER TABLE [dbo].[cart_details]  WITH CHECK ADD  CONSTRAINT [FK_cart_details_products] FOREIGN KEY([product_id])
REFERENCES [dbo].[products] ([product_id])
GO
ALTER TABLE [dbo].[cart_details] CHECK CONSTRAINT [FK_cart_details_products]
GO
ALTER TABLE [dbo].[carts]  WITH CHECK ADD  CONSTRAINT [FK_carts_Users] FOREIGN KEY([user_id])
REFERENCES [dbo].[Users] ([user_id])
GO
ALTER TABLE [dbo].[carts] CHECK CONSTRAINT [FK_carts_Users]
GO
ALTER TABLE [dbo].[comments]  WITH CHECK ADD  CONSTRAINT [FK_comments_products] FOREIGN KEY([product_id])
REFERENCES [dbo].[products] ([product_id])
GO
ALTER TABLE [dbo].[comments] CHECK CONSTRAINT [FK_comments_products]
GO
ALTER TABLE [dbo].[comments]  WITH CHECK ADD  CONSTRAINT [FK_comments_Users] FOREIGN KEY([user_id])
REFERENCES [dbo].[Users] ([user_id])
GO
ALTER TABLE [dbo].[comments] CHECK CONSTRAINT [FK_comments_Users]
GO
ALTER TABLE [dbo].[ConfirmationCode]  WITH CHECK ADD  CONSTRAINT [FK_ConfirmationCode_Users] FOREIGN KEY([user_id])
REFERENCES [dbo].[Users] ([user_id])
GO
ALTER TABLE [dbo].[ConfirmationCode] CHECK CONSTRAINT [FK_ConfirmationCode_Users]
GO
ALTER TABLE [dbo].[order_details]  WITH CHECK ADD  CONSTRAINT [FK_order_details_orders] FOREIGN KEY([order_id])
REFERENCES [dbo].[orders] ([order_id])
GO
ALTER TABLE [dbo].[order_details] CHECK CONSTRAINT [FK_order_details_orders]
GO
ALTER TABLE [dbo].[order_details]  WITH CHECK ADD  CONSTRAINT [FK_order_details_products] FOREIGN KEY([product_id])
REFERENCES [dbo].[products] ([product_id])
GO
ALTER TABLE [dbo].[order_details] CHECK CONSTRAINT [FK_order_details_products]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FK_orders_Users] FOREIGN KEY([user_id])
REFERENCES [dbo].[Users] ([user_id])
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FK_orders_Users]
GO
ALTER TABLE [dbo].[payment]  WITH CHECK ADD  CONSTRAINT [FK_payment_orders] FOREIGN KEY([order_id])
REFERENCES [dbo].[orders] ([order_id])
GO
ALTER TABLE [dbo].[payment] CHECK CONSTRAINT [FK_payment_orders]
GO
ALTER TABLE [dbo].[product_reviews]  WITH CHECK ADD  CONSTRAINT [FK_product_reviews_products] FOREIGN KEY([product_id])
REFERENCES [dbo].[products] ([product_id])
GO
ALTER TABLE [dbo].[product_reviews] CHECK CONSTRAINT [FK_product_reviews_products]
GO
ALTER TABLE [dbo].[product_reviews]  WITH CHECK ADD  CONSTRAINT [FK_product_reviews_Users] FOREIGN KEY([user_id])
REFERENCES [dbo].[Users] ([user_id])
GO
ALTER TABLE [dbo].[product_reviews] CHECK CONSTRAINT [FK_product_reviews_Users]
GO
ALTER TABLE [dbo].[products]  WITH CHECK ADD  CONSTRAINT [FK_products_brands] FOREIGN KEY([brand_id])
REFERENCES [dbo].[brands] ([brand_id])
GO
ALTER TABLE [dbo].[products] CHECK CONSTRAINT [FK_products_brands]
GO
ALTER TABLE [dbo].[products]  WITH CHECK ADD  CONSTRAINT [FK_products_categorys] FOREIGN KEY([category_id])
REFERENCES [dbo].[categorys] ([category_id])
GO
ALTER TABLE [dbo].[products] CHECK CONSTRAINT [FK_products_categorys]
GO
ALTER TABLE [dbo].[products]  WITH CHECK ADD  CONSTRAINT [FK_products_suppliers] FOREIGN KEY([supplier_id])
REFERENCES [dbo].[suppliers] ([supplier_id])
GO
ALTER TABLE [dbo].[products] CHECK CONSTRAINT [FK_products_suppliers]
GO
ALTER TABLE [dbo].[Replys]  WITH CHECK ADD  CONSTRAINT [FK_Replys_comments] FOREIGN KEY([comment_id])
REFERENCES [dbo].[comments] ([comment_id])
GO
ALTER TABLE [dbo].[Replys] CHECK CONSTRAINT [FK_Replys_comments]
GO
ALTER TABLE [dbo].[Replys]  WITH CHECK ADD  CONSTRAINT [FK_Replys_Users] FOREIGN KEY([responder_id])
REFERENCES [dbo].[Users] ([user_id])
GO
ALTER TABLE [dbo].[Replys] CHECK CONSTRAINT [FK_Replys_Users]
GO
ALTER TABLE [dbo].[Replys]  WITH CHECK ADD  CONSTRAINT [FK_Replys_Users1] FOREIGN KEY([receiver_id])
REFERENCES [dbo].[Users] ([user_id])
GO
ALTER TABLE [dbo].[Replys] CHECK CONSTRAINT [FK_Replys_Users1]
GO
ALTER TABLE [dbo].[shippings]  WITH CHECK ADD  CONSTRAINT [FK_shippings_orders] FOREIGN KEY([transport_id])
REFERENCES [dbo].[orders] ([order_id])
GO
ALTER TABLE [dbo].[shippings] CHECK CONSTRAINT [FK_shippings_orders]
GO
ALTER TABLE [dbo].[shippings]  WITH CHECK ADD  CONSTRAINT [FK_shippings_Users] FOREIGN KEY([user_id])
REFERENCES [dbo].[Users] ([user_id])
GO
ALTER TABLE [dbo].[shippings] CHECK CONSTRAINT [FK_shippings_Users]
GO
USE [master]
GO
ALTER DATABASE [electric_project] SET  READ_WRITE 
GO
