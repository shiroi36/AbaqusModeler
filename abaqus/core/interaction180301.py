





#------------------------------------------------------------------------------------


mdb.models['Model-1'].ContactProperty('IntProp-1')
mdb.models['Model-1'].interactionProperties['IntProp-1'].TangentialBehavior(
formulation=PENALTY, directionality=ISOTROPIC, slipRateDependency=OFF, 
pressureDependency=OFF, temperatureDependency=OFF, dependencies=0, 
table=((0.45, ), ), shearStressLimit=None, maximumElasticSlip=FRACTION, 
fraction=0.005, elasticSlipStiffness=None)

#------------------------------------------------------------------------------------
#ボルトサーフェスの作成
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt00L'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['bfbolt01L'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['bfbolt02L'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['bfbolt03L'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s5 = a.instances['bfbolt04L'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s6 = a.instances['bfbolt05L'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6, name='b0l')

a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt06L'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['bfbolt07L'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['bfbolt08L'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['bfbolt09L'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s5 = a.instances['bfbolt10L'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s6 = a.instances['bfbolt11L'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6, name='b1l')

a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt12L'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['bfbolt13L'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['bfbolt14L'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['bfbolt15L'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s5 = a.instances['bfbolt16L'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s6 = a.instances['bfbolt17L'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6, name='b2l')

a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt18L'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['bfbolt19L'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['bfbolt20L'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['bfbolt21L'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s5 = a.instances['bfbolt22L'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s6 = a.instances['bfbolt23L'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6, name='b3l')


a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt00L'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['cwbolt01L'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['cwbolt02L'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['cwbolt03L'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s5 = a.instances['cwbolt04L'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s6 = a.instances['cwbolt05L'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6, name='b4l')

a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt06L'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['cwbolt07L'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['cwbolt08L'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['cwbolt09L'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s5 = a.instances['cwbolt10L'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s6 = a.instances['cwbolt11L'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6, name='b5l')

a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bwbolt00L'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['bwbolt01L'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['bwbolt02L'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['bwbolt03L'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s5 = a.instances['bwbolt04L'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s6 = a.instances['bwbolt05L'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s7 = a.instances['bwbolt06L'].faces
side1Faces7 = s7.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6+side1Faces7, name='b6l')

#------------------------------------------------------------------------------------
#ボルトサーフェスの作成
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt00R'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['bfbolt01R'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['bfbolt02R'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['bfbolt03R'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s5 = a.instances['bfbolt04R'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s6 = a.instances['bfbolt05R'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6, name='b0r')

a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt06R'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['bfbolt07R'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['bfbolt08R'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['bfbolt09R'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s5 = a.instances['bfbolt10R'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s6 = a.instances['bfbolt11R'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6, name='b1r')

a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt12R'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['bfbolt13R'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['bfbolt14R'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['bfbolt15R'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s5 = a.instances['bfbolt16R'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s6 = a.instances['bfbolt17R'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6, name='b2r')

a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt18R'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['bfbolt19R'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['bfbolt20R'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['bfbolt21R'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s5 = a.instances['bfbolt22R'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s6 = a.instances['bfbolt23R'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6, name='b3r')


a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt00R'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['cwbolt01R'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['cwbolt02R'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['cwbolt03R'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s5 = a.instances['cwbolt04R'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s6 = a.instances['cwbolt05R'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6, name='b4r')

a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt06R'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['cwbolt07R'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['cwbolt08R'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['cwbolt09R'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s5 = a.instances['cwbolt10R'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s6 = a.instances['cwbolt11R'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6, name='b5r')

a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bwbolt00R'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['bwbolt01R'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['bwbolt02R'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['bwbolt03R'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s5 = a.instances['bwbolt04R'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s6 = a.instances['bwbolt05R'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s7 = a.instances['bwbolt06R'].faces
side1Faces7 = s7.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6+side1Faces7, name='b6r')


a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d0'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#c0000000 #1000007 ]', ), )
s2 = a.instances['kpl-u1'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#c0000000 #1000007 ]', ), )
s3 = a.instances['sp0'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#1fc0000 ]', ), )
s4 = a.instances['sp1'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#1fc0000 ]', ), )
s5 = a.instances['kpl-u0'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#c0000000 #1000007 ]', ), )
s6 = a.instances['kpr-d1'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#c0000000 #1000007 ]', ), )
s7 = a.instances['b1'].faces
side1Faces7 = s7.getSequenceFromMask(mask=('[#7f ]', ), )
s8 = a.instances['b0'].faces
side1Faces8 = s8.getSequenceFromMask(mask=('[#7f ]', ), )
s9 = a.instances['kpl-d1'].faces
side1Faces9 = s9.getSequenceFromMask(mask=('[#c0000000 #1000007 ]', ), )
s10 = a.instances['kpr-u0'].faces
side1Faces10 = s10.getSequenceFromMask(mask=('[#c0000000 #1000007 ]', ), )
s11 = a.instances['kpr-u1'].faces
side1Faces11 = s11.getSequenceFromMask(mask=('[#c0000000 #1000007 ]', ), )
s12 = a.instances['kpl-d0'].faces
side1Faces12 = s12.getSequenceFromMask(mask=('[#c0000000 #1000007 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6+side1Faces7+side1Faces8+side1Faces9+\
side1Faces10+side1Faces11+side1Faces12, name='p0')



a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b0'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#f1f8fc00 #3 ]', ), )
s2 = a.instances['kpl-d0'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#1f000 #0 #4000000 ]', ), )
s3 = a.instances['kpr-d0'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#1f000 #0 #4000000 ]', ), )
s4 = a.instances['kpl-u0'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#1f000 #0 #4000000 ]', ), )
s5 = a.instances['kpr-u0'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#1f000 #0 #4000000 ]', ), )
s6 = a.instances['b1'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#f1f8fc00 #3 ]', ), )
s7 = a.instances['kpl-d1'].faces
side1Faces7 = s7.getSequenceFromMask(mask=('[#1f000 #0 #4000000 ]', ), )
s8 = a.instances['kpr-d1'].faces
side1Faces8 = s8.getSequenceFromMask(mask=('[#1f000 #0 #4000000 ]', ), )
s9 = a.instances['kpl-u1'].faces
side1Faces9 = s9.getSequenceFromMask(mask=('[#1f000 #0 #4000000 ]', ), )
s10 = a.instances['kpr-u1'].faces
side1Faces10 = s10.getSequenceFromMask(mask=('[#1f000 #0 #4000000 ]', ), )
side2Faces1 = s1.getSequenceFromMask(mask=('[#0 #7e0 ]', ), )
side2Faces6 = s6.getSequenceFromMask(mask=('[#0 #7e0 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6+side1Faces7+side1Faces8+side1Faces9+\
side1Faces10, side2Faces=side2Faces1+side2Faces6, name='p1')



a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b0'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#e3f1f800 #7 ]', ), )
s2 = a.instances['kpl-d0'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#1f000 #0 #4000000 ]', ), )
s3 = a.instances['kpl-u0'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#1f000 #0 #4000000 ]', ), )
s4 = a.instances['kpr-u0'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#1f000 #0 #4000000 ]', ), )
s5 = a.instances['b1'].faces
side1Faces5 = s5.getSequenceFromMask(mask=('[#e3f1f800 #7 ]', ), )
s6 = a.instances['kpl-d1'].faces
side1Faces6 = s6.getSequenceFromMask(mask=('[#1f000 #0 #4000000 ]', ), )
s7 = a.instances['kpr-d1'].faces
side1Faces7 = s7.getSequenceFromMask(mask=('[#1f000 #0 #4000000 ]', ), )
s8 = a.instances['kpl-u1'].faces
side1Faces8 = s8.getSequenceFromMask(mask=('[#1f000 #0 #4000000 ]', ), )
s9 = a.instances['kpr-u1'].faces
side1Faces9 = s9.getSequenceFromMask(mask=('[#1f000 #0 #4000000 ]', ), )
s10 = a.instances['kpr-d0'].faces
side1Faces10 = s10.getSequenceFromMask(mask=('[#1f000 #0 #4000000 ]', ), )
side2Faces1 = s1.getSequenceFromMask(mask=('[#0 #fc0 ]', ), )
side2Faces5 = s5.getSequenceFromMask(mask=('[#0 #fc0 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5+side1Faces6+side1Faces7+side1Faces8+side1Faces9+\
side1Faces10, side2Faces=side2Faces1+side2Faces5, name='p1')








#------------------------------------------------------------------------------------
#結合の設定

a = mdb.models['Model-1'].rootAssembly
region1=a.instances['cfr0'].sets['Set-1']
a = mdb.models['Model-1'].rootAssembly
region2=a.instances['col'].surfaces['c11']
mdb.models['Model-1'].Tie(name='c4', master=region1, slave=region2, 
positionToleranceMethod=SPECIFIED, positionTolerance=5.0, adjust=OFF, 
tieRotations=ON, thickness=ON)
a = mdb.models['Model-1'].rootAssembly
region1=a.instances['cfr3'].sets['Set-1']
a = mdb.models['Model-1'].rootAssembly
region2=a.instances['col'].surfaces['c8']
mdb.models['Model-1'].Tie(name='c5', master=region1, slave=region2, 
positionToleranceMethod=SPECIFIED, positionTolerance=5.0, adjust=OFF, 
tieRotations=ON, thickness=ON)
a = mdb.models['Model-1'].rootAssembly
region1=a.instances['cfr2'].sets['Set-1']
a = mdb.models['Model-1'].rootAssembly
region2=a.instances['col'].surfaces['c9']
mdb.models['Model-1'].Tie(name='c6', master=region1, slave=region2, 
positionToleranceMethod=SPECIFIED, positionTolerance=5.0, adjust=OFF, 
tieRotations=ON, thickness=ON)
a = mdb.models['Model-1'].rootAssembly
region1=a.instances['cfr1'].sets['Set-1']
a = mdb.models['Model-1'].rootAssembly
region2=a.instances['col'].surfaces['c10']
mdb.models['Model-1'].Tie(name='c7', master=region1, slave=region2, 
positionToleranceMethod=SPECIFIED, positionTolerance=5.0, adjust=OFF, 
tieRotations=ON, thickness=ON)

a = mdb.models['Model-1'].rootAssembly
region1=a.instances['sp0'].surfaces['e']
a = mdb.models['Model-1'].rootAssembly
region2=a.instances['col'].surfaces['c13']
mdb.models['Model-1'].Tie(name='c8', master=region1, slave=region2, 
positionToleranceMethod=SPECIFIED, positionTolerance=5.0, adjust=OFF, 
tieRotations=ON, thickness=ON)
a = mdb.models['Model-1'].rootAssembly
region1=a.instances['sp1'].surfaces['e']
a = mdb.models['Model-1'].rootAssembly
region2=a.instances['col'].surfaces['c12']
mdb.models['Model-1'].Tie(name='c9', master=region1, slave=region2, 
positionToleranceMethod=SPECIFIED, positionTolerance=5.0, adjust=OFF, 
tieRotations=ON, thickness=ON)

#------------------------------------------------------------------------------------
#載荷点の設定

a = mdb.models['Model-1'].rootAssembly
a.ReferencePoint(point=(0.0, lc/2, 0.0))
a = mdb.models['Model-1'].rootAssembly
a.ReferencePoint(point=(0.0, -lc/2, 0.0))
a = mdb.models['Model-1'].rootAssembly
a.ReferencePoint(point=(lb2/2, 0.0, 0.0))
a = mdb.models['Model-1'].rootAssembly
a.ReferencePoint(point=(-lb2/2, 0.0, 0.0))


#------------------------------------------------------------------------------------
#接触の設定


mdb.models['Model-1'].ContactExp(name='Int-1', createStepName='Initial')
r11=mdb.models['Model-1'].rootAssembly.surfaces['b0l']
r12=mdb.models['Model-1'].rootAssembly.instances['b0'].surfaces['b2']
r21=mdb.models['Model-1'].rootAssembly.surfaces['b0l']
r22=mdb.models['Model-1'].rootAssembly.instances['kpl-d0'].surfaces['b2']
r31=mdb.models['Model-1'].rootAssembly.surfaces['b0r']
r32=mdb.models['Model-1'].rootAssembly.instances['b1'].surfaces['b2']
r41=mdb.models['Model-1'].rootAssembly.surfaces['b0r']
r42=mdb.models['Model-1'].rootAssembly.instances['kpl-d1'].surfaces['b2']
r51=mdb.models['Model-1'].rootAssembly.surfaces['b1l']
r52=mdb.models['Model-1'].rootAssembly.instances['b0'].surfaces['b1']
r61=mdb.models['Model-1'].rootAssembly.surfaces['b1l']
r62=mdb.models['Model-1'].rootAssembly.instances['kpr-d0'].surfaces['b2']
r71=mdb.models['Model-1'].rootAssembly.surfaces['b1r']
r72=mdb.models['Model-1'].rootAssembly.instances['b1'].surfaces['b1']
r81=mdb.models['Model-1'].rootAssembly.surfaces['b1r']
r82=mdb.models['Model-1'].rootAssembly.instances['kpr-d1'].surfaces['b2']
r91=mdb.models['Model-1'].rootAssembly.surfaces['b2l']
r92=mdb.models['Model-1'].rootAssembly.instances['b0'].surfaces['b4']
r101=mdb.models['Model-1'].rootAssembly.surfaces['b2l']
r102=mdb.models['Model-1'].rootAssembly.instances['kpr-u0'].surfaces['b2']
r111=mdb.models['Model-1'].rootAssembly.surfaces['b2r']
r112=mdb.models['Model-1'].rootAssembly.instances['b1'].surfaces['b4']
r121=mdb.models['Model-1'].rootAssembly.surfaces['b2r']
r122=mdb.models['Model-1'].rootAssembly.instances['kpr-u1'].surfaces['b2']
r131=mdb.models['Model-1'].rootAssembly.surfaces['b3l']
r132=mdb.models['Model-1'].rootAssembly.instances['b0'].surfaces['b3']
r141=mdb.models['Model-1'].rootAssembly.surfaces['b3l']
r142=mdb.models['Model-1'].rootAssembly.instances['kpl-u0'].surfaces['b2']
r151=mdb.models['Model-1'].rootAssembly.surfaces['b3r']
r152=mdb.models['Model-1'].rootAssembly.instances['b1'].surfaces['b3']
r161=mdb.models['Model-1'].rootAssembly.surfaces['b3r']
r162=mdb.models['Model-1'].rootAssembly.instances['kpl-u1'].surfaces['b2']
mdb.models['Model-1'].interactions['Int-1'].includedPairs.setValuesInStep(
stepName='Initial', useAllstar=OFF, addPairs=((r11, r12), (r21, r22), (
r31, r32), (r41, r42), (r51, r52), (r61, r62), (r71, r72), (r81, r82), 
(r91, r92), (r101, r102), (r111, r112), (r121, r122), (r131, r132), (
r141, r142), (r151, r152), (r161, r162)))
mdb.models['Model-1'].interactions['Int-1'].contactPropertyAssignments.appendInStep(
stepName='Initial', assignments=((GLOBAL, SELF, 'IntProp-1'), ))

r11=mdb.models['Model-1'].rootAssembly.surfaces['b4l']
r12=mdb.models['Model-1'].rootAssembly.instances['col'].surfaces['c2']
r21=mdb.models['Model-1'].rootAssembly.surfaces['b4l']
r22=mdb.models['Model-1'].rootAssembly.instances['cwr1'].surfaces['b1']
r31=mdb.models['Model-1'].rootAssembly.surfaces['b4l']
r32=mdb.models['Model-1'].rootAssembly.instances['kpl-d0'].surfaces['b1']
r41=mdb.models['Model-1'].rootAssembly.surfaces['b4l']
r42=mdb.models['Model-1'].rootAssembly.instances['kpr-d0'].surfaces['b1']
r51=mdb.models['Model-1'].rootAssembly.surfaces['b4l']
r52=mdb.models['Model-1'].rootAssembly.instances['panel1'].surfaces['b0']
r61=mdb.models['Model-1'].rootAssembly.surfaces['b4r']
r62=mdb.models['Model-1'].rootAssembly.instances['col'].surfaces['c1']
r71=mdb.models['Model-1'].rootAssembly.surfaces['b4r']
r72=mdb.models['Model-1'].rootAssembly.instances['cwr0'].surfaces['b0']
r81=mdb.models['Model-1'].rootAssembly.surfaces['b4r']
r82=mdb.models['Model-1'].rootAssembly.instances['kpl-d1'].surfaces['b1']
r91=mdb.models['Model-1'].rootAssembly.surfaces['b4r']
r92=mdb.models['Model-1'].rootAssembly.instances['kpr-d1'].surfaces['b1']
r101=mdb.models['Model-1'].rootAssembly.surfaces['b4r']
r102=mdb.models['Model-1'].rootAssembly.instances['panel1'].surfaces['b3']
r111=mdb.models['Model-1'].rootAssembly.surfaces['b5l']
r112=mdb.models['Model-1'].rootAssembly.instances['col'].surfaces['c0']
r121=mdb.models['Model-1'].rootAssembly.surfaces['b5l']
r122=mdb.models['Model-1'].rootAssembly.instances['cwr0'].surfaces['b1']
r131=mdb.models['Model-1'].rootAssembly.surfaces['b5l']
r132=mdb.models['Model-1'].rootAssembly.instances['kpl-u0'].surfaces['b1']
r141=mdb.models['Model-1'].rootAssembly.surfaces['b5l']
r142=mdb.models['Model-1'].rootAssembly.instances['kpr-u0'].surfaces['b1']
r151=mdb.models['Model-1'].rootAssembly.surfaces['b5l']
r152=mdb.models['Model-1'].rootAssembly.instances['panel1'].surfaces['b2']
r161=mdb.models['Model-1'].rootAssembly.surfaces['b5r']
r162=mdb.models['Model-1'].rootAssembly.instances['col'].surfaces['c3']
r171=mdb.models['Model-1'].rootAssembly.surfaces['b5r']
r172=mdb.models['Model-1'].rootAssembly.instances['cwr1'].surfaces['b0']
r181=mdb.models['Model-1'].rootAssembly.surfaces['b5r']
r182=mdb.models['Model-1'].rootAssembly.instances['kpl-u1'].surfaces['b1']
r191=mdb.models['Model-1'].rootAssembly.surfaces['b5r']
r192=mdb.models['Model-1'].rootAssembly.instances['kpr-u1'].surfaces['b1']
r201=mdb.models['Model-1'].rootAssembly.surfaces['b5r']
r202=mdb.models['Model-1'].rootAssembly.instances['panel1'].surfaces['b1']
r211=mdb.models['Model-1'].rootAssembly.surfaces['b6l']
r212=mdb.models['Model-1'].rootAssembly.instances['b0'].surfaces['b5']
r221=mdb.models['Model-1'].rootAssembly.surfaces['b6l']
r222=mdb.models['Model-1'].rootAssembly.instances['sp0'].surfaces['b1']
r231=mdb.models['Model-1'].rootAssembly.surfaces['b6r']
r232=mdb.models['Model-1'].rootAssembly.instances['b1'].surfaces['b5']
r241=mdb.models['Model-1'].rootAssembly.surfaces['b6r']
r242=mdb.models['Model-1'].rootAssembly.instances['sp1'].surfaces['b1']
mdb.models['Model-1'].interactions['Int-1'].includedPairs.setValuesInStep(
stepName='Initial', addPairs=((r11, r12), (r21, r22), (r31, r32), (r41, 
r42), (r51, r52), (r61, r62), (r71, r72), (r81, r82), (r91, r92), (
r101, r102), (r111, r112), (r121, r122), (r131, r132), (r141, r142), (
r151, r152), (r161, r162), (r171, r172), (r181, r182), (r191, r192), (
r201, r202), (r211, r212), (r221, r222), (r231, r232), (r241, r242)))




#------------------------------------------------------------------------------------
#剛体の設定

a = mdb.models['Model-1'].rootAssembly
r1 = a.referencePoints
refPoints1=(r1[327], )
a.Set(referencePoints=refPoints1, name='RP1')
a = mdb.models['Model-1'].rootAssembly
r1 = a.referencePoints
refPoints1=(r1[328], )
a.Set(referencePoints=refPoints1, name='RP2')
a = mdb.models['Model-1'].rootAssembly
r1 = a.referencePoints
refPoints1=(r1[329], )
a.Set(referencePoints=refPoints1, name='RP3')
a = mdb.models['Model-1'].rootAssembly
r1 = a.referencePoints
refPoints1=(r1[330], )
a.Set(referencePoints=refPoints1, name='RP4')

a = mdb.models['Model-1'].rootAssembly
region3=a.instances['col'].sets['e0']
a = mdb.models['Model-1'].rootAssembly
region1=a.sets['RP1']
mdb.models['Model-1'].RigidBody(name='R0', refPointRegion=region1, 
pinRegion=region3)

a = mdb.models['Model-1'].rootAssembly
region3=a.instances['col'].sets['e1']
a = mdb.models['Model-1'].rootAssembly
region1=a.sets['RP2']
mdb.models['Model-1'].RigidBody(name='R1', refPointRegion=region1, 
pinRegion=region3)

a = mdb.models['Model-1'].rootAssembly
region3=a.instances['b1'].sets['e']
a = mdb.models['Model-1'].rootAssembly
region1=a.sets['RP3']
mdb.models['Model-1'].RigidBody(name='R2', refPointRegion=region1, 
pinRegion=region3)

a = mdb.models['Model-1'].rootAssembly
region3=a.instances['b0'].sets['e']
a = mdb.models['Model-1'].rootAssembly
region1=a.sets['RP4']
mdb.models['Model-1'].RigidBody(name='R3', refPointRegion=region1, 
pinRegion=region3)






