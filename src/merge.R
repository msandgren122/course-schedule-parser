install.packages("data.table")
install.packages("tidyverse")
install.packages("RCurl")

library(data.table)
library(tidyverse)
library(RCurl)

df <- fread("fa10.csv", skip = 13)

df <- read.csv("https://raw.githubusercontent.com/msandgren122/course-schedule-parser/master/csv/fa10.csv",
               skip = 13)

list.files("https://raw.githubusercontent.com/msandgren122/course-schedule-parser/master/csv")

url <- getURL("https://github.com/msandgren122/course-schedule-parser/tree/master/csv")
