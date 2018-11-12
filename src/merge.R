#----------------------------------------------------------------------
# libraries
#----------------------------------------------------------------------
install.packages("data.table")
install.packages("tidyverse")
install.packages("RCurl")
install.packages("ggplot2")

library(data.table)
library(tidyverse)
library(RCurl)
library(ggplot2)





#----------------------------------------------------------------------
# what am I trying to do
#----------------------------------------------------------------------
#plot per professor, by year, the total students enrolled per course. 
# so line graph with time on x, # enrolled on y, and multiple lines,
#one for each course

#compare # of students registered for credit/no credit for 
# folk ensembles, orchestra, chapel choir, wind ensemble, chamber music,
# saxophone quartet, woodwind quintet





#----------------------------------------------------------------------
# read data
#----------------------------------------------------------------------
main <- fread("main.csv")
main <- as.tibble(main)





#----------------------------------------------------------------------
# the dirty work...fix the stupid credits column
#----------------------------------------------------------------------

#fix the # of credits. If it's a single number, just convert it to numeric
# If it's 2/3 or some shit, convert it to the average of those two numbers
credit_fix <- function(s) {
 
   result = tryCatch({
    as.numeric(s)
  }, warning = function(w) {
    
    s <- gsub("[-/]", "", s)
    
    #put all the numbers in a vector
    nums <- c()
    
    for (i in 1:nchar(s)) {
      n <- substr(s, i, i)
      nums <- append(nums, as.numeric(n))
    }
    
    #return the average
    mean(nums)
  })
  as.numeric(result)
}



# I can't figure out another way so I'm looping, damnnit
for(i in 1:nrow(main)) {
  main$Credits[i] <- credit_fix(main$Credits[i])
}



# have to manually convert to numeric, even though my function does it
# You have to convert a whole column's type at once, you can't convert
# each elements type in a loop
main <- mutate(main, Credits = as.numeric(Credits))



#sanity check...hooray, Credits is a double
main



#another sanity check
sort(unique(main$Instructor))






#----------------------------------------------------------------------
# now the fun stuff
#----------------------------------------------------------------------

#a useful thing you can do
filter(main, Instructor == "Talaga Stephen C.")



#totals per class, per professor, per year
agg <- 
  main %>%
  group_by(Instructor, Title, Year) %>% 
  summarise(nstudents = sum(Actual))
View(agg)






#use just one prof to get a nice looking graph. Facets later
southard <- filter(agg, Instructor == "Southard Robert G.")
southard

ggplot(southard, aes(x = Year, y = nstudents, group = Title)) +
  geom_line(aes(color = Title))
  


