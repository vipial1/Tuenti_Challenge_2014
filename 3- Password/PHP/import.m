function [a,x,y,z]=import()
clear all 
a=importdata('valores.txt', ' ');
b=cell2mat(strfind(a,';'));

d= length(a);
x=a(1:3:d);
y=a(2:3:d);
z=a(3:3:d);

