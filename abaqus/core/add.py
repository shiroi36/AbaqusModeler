# -*- coding: mbcs -*-
# Do not delete the following import lines
from abaqus import *
from abaqusConstants import *
import __main__

import section
import regionToolset
import displayGroupMdbToolset as dgm
import part
import material
import assembly
import step
import interaction
import load
import mesh
import optimization
import job
import sketch
import visualization
import xyPlot
import displayGroupOdbToolset as dgo
import connectorBehavior
import os


a = mdb.models['Model-1'].rootAssembly
c1 = a.instances['kpl-d'].cells
cells1 = c1.getSequenceFromMask(mask=('[#800 ]', ), )
c2 = a.instances['kpr-d'].cells
cells2 = c2.getSequenceFromMask(mask=('[#800 ]', ), )
c3 = a.instances['kpl-u'].cells
cells3 = c3.getSequenceFromMask(mask=('[#800 ]', ), )
c4 = a.instances['kpr-u'].cells
cells4 = c4.getSequenceFromMask(mask=('[#800 ]', ), )
c5 = a.instances['b'].cells
cells5 = c5.getSequenceFromMask(mask=('[#2040 #20000000 #200 ]', ), )
a.Set(cells=cells1+cells2+cells3+cells4+cells5, name='outputelement')
a = mdb.models['Model-1'].rootAssembly
f1 = a.instances['b'].faces
faces1 = f1.getSequenceFromMask(mask=(
'[#ffffffff:10 #75ffffff #ee573398 #56aa954a #fafd7ead #7ffdfffb #fffffdfd', 
' #87f09f ]', ), )
c2 = a.instances['kpl-d'].cells
cells2 = c2.getSequenceFromMask(mask=('[#800 ]', ), )
c3 = a.instances['kpr-d'].cells
cells3 = c3.getSequenceFromMask(mask=('[#800 ]', ), )
c4 = a.instances['kpl-u'].cells
cells4 = c4.getSequenceFromMask(mask=('[#800 ]', ), )
c5 = a.instances['kpr-u'].cells
cells5 = c5.getSequenceFromMask(mask=('[#800 ]', ), )
r6 = a.referencePoints
refPoints6=(r6[182], r6[183], r6[184], )
a.Set(faces=faces1, cells=cells2+cells3+cells4+cells5, 
referencePoints=refPoints6, name='outputnode_u')


a = mdb.models['Model-1'].rootAssembly
c1 = a.instances['b'].cells
cells1 = c1.getSequenceFromMask(mask=('[#2040 #20000000 #2000200 #4 ]', ), )
c2 = a.instances['kpl-d'].cells
cells2 = c2.getSequenceFromMask(mask=('[#800 ]', ), )
c3 = a.instances['kpr-d'].cells
cells3 = c3.getSequenceFromMask(mask=('[#800 ]', ), )
c4 = a.instances['kpl-u'].cells
cells4 = c4.getSequenceFromMask(mask=('[#800 ]', ), )
c5 = a.instances['kpr-u'].cells
cells5 = c5.getSequenceFromMask(mask=('[#800 ]', ), )
a.Set(cells=cells1+cells2+cells3+cells4+cells5, name='outputelement')





a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#f0000000 #7 #0 #20202010 #310000 #0:4 #80000', ' #0:4 #55500000 ]', 
), )
a.Surface(side1Faces=side1Faces1, name='CP-1-b')
region1=a.surfaces['CP-1-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#555208 #100 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-1-kpl-d')
region2=a.surfaces['CP-1-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#0:6 #1fc #0 #42020202 #11 #0:4 #2aa80000', ' #1 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-2-b')
region3=a.surfaces['CP-2-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#3f800002 #80 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-2-kpr-d')
region4=a.surfaces['CP-2-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#0:5 #7f00000 #0 #20000000 #202020 #4003100 #0:4', ' #5550 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-3-b')
region5=a.surfaces['CP-3-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#555208 #100 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-3-kpl-u')
region6=a.surfaces['CP-3-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#0 #1fc00 #0 #2020200 #1142 #0:9 #1aaa0 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-4-b')
region7=a.surfaces['CP-4-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#3f800002 #80 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-4-kpr-u')
region8=a.surfaces['CP-4-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#0 #10c0000 #0 #100 #0:9 #10200 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-5-b')
region9=a.surfaces['CP-5-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt00'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-5-bfbolt00')
region10=a.surfaces['CP-5-bfbolt00']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#0 #1180000 #0 #10000 #0:9 #10400 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-6-b')
region11=a.surfaces['CP-6-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt01'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-6-bfbolt01')
region12=a.surfaces['CP-6-bfbolt01']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#0 #1300000 #0 #1000000 #0:9 #10800 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-7-b')
region13=a.surfaces['CP-7-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt02'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-7-bfbolt02')
region14=a.surfaces['CP-7-bfbolt02']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#0 #1600000 #0:2 #1 #0:8 #11000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-8-b')
region15=a.surfaces['CP-8-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt03'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-8-bfbolt03')
region16=a.surfaces['CP-8-bfbolt03']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#0 #1c00000 #0:2 #20 #0:8 #12000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-9-b')
region17=a.surfaces['CP-9-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt04'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-9-bfbolt04')
region18=a.surfaces['CP-9-bfbolt04']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#0 #1800000 #0:2 #a00 #0:8 #14000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-10-b')
region19=a.surfaces['CP-10-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt05'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-10-bfbolt05')
region20=a.surfaces['CP-10-bfbolt05']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#0:5 #c0000 #0:3 #280 #0:3 #8000000 #2 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-11-b')
region21=a.surfaces['CP-11-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt06'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-11-bfbolt06')
region22=a.surfaces['CP-11-bfbolt06']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#0:5 #e0000 #0:3 #4000 #0:3 #10000000 #2 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-12-b')
region23=a.surfaces['CP-12-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt07'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-12-bfbolt07')
region24=a.surfaces['CP-12-bfbolt07']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#0:5 #b0000 #0:2 #400000 #0:4 #20000000 #2 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-13-b')
region25=a.surfaces['CP-13-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt08'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-13-bfbolt08')
region26=a.surfaces['CP-13-bfbolt08']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#0:5 #98000 #0:2 #4000 #0:4 #40000000 #2 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-14-b')
region27=a.surfaces['CP-14-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt09'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-14-bfbolt09')
region28=a.surfaces['CP-14-bfbolt09']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#0:5 #8c000 #0:2 #40 #0:4 #80000000 #2 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-15-b')
region29=a.surfaces['CP-15-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt10'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-15-bfbolt10')
region30=a.surfaces['CP-15-bfbolt10']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#0:5 #86000 #0 #10000000 #0:6 #3 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-16-b')
region31=a.surfaces['CP-16-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt11'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-16-bfbolt11')
region32=a.surfaces['CP-16-bfbolt11']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#6000000 #0:3 #28000 #0:8 #2040000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-17-b')
region33=a.surfaces['CP-17-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt12'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-17-bfbolt12')
region34=a.surfaces['CP-17-bfbolt12']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#7000000 #0:3 #420000 #0:8 #80000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-18-b')
region35=a.surfaces['CP-18-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt13'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-18-bfbolt13')
region36=a.surfaces['CP-18-bfbolt13']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#5800000 #0:2 #40000000 #20000 #0:8 #100000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-19-b')
region37=a.surfaces['CP-19-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt14'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-19-bfbolt14')
region38=a.surfaces['CP-19-bfbolt14']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#4c00000 #0:2 #400000 #20000 #0:8 #200000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-20-b')
region39=a.surfaces['CP-20-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt15'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-20-bfbolt15')
region40=a.surfaces['CP-20-bfbolt15']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#4600000 #0:2 #4000 #20000 #0:8 #400000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-21-b')
region41=a.surfaces['CP-21-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt16'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-21-bfbolt16')
region42=a.surfaces['CP-21-bfbolt16']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#4300000 #0:2 #20 #20000 #0:8 #800000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-22-b')
region43=a.surfaces['CP-22-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt17'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-22-bfbolt17')
region44=a.surfaces['CP-22-bfbolt17']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0:6 #10c00 #0 #1 #0:6 #104 ]', ), 
)
a.Surface(side1Faces=side1Faces1, name='CP-23-b')
region45=a.surfaces['CP-23-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt18'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-23-bfbolt18')
region46=a.surfaces['CP-23-bfbolt18']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0:6 #11800 #0 #100 #0:6 #108 ]', 
), )
a.Surface(side1Faces=side1Faces1, name='CP-24-b')
region47=a.surfaces['CP-24-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt19'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-24-bfbolt19')
region48=a.surfaces['CP-24-bfbolt19']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#0:6 #13000 #0 #10000 #0:6 #110 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-25-b')
region49=a.surfaces['CP-25-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt20'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-25-bfbolt20')
region50=a.surfaces['CP-25-bfbolt20']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#0:6 #16000 #0 #1000000 #0:6 #120 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-26-b')
region51=a.surfaces['CP-26-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt21'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-26-bfbolt21')
region52=a.surfaces['CP-26-bfbolt21']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#0:6 #1c000 #0 #20000000 #0:6 #140 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-27-b')
region53=a.surfaces['CP-27-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt22'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-27-bfbolt22')
region54=a.surfaces['CP-27-bfbolt22']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0:6 #18000 #0:2 #a #0:5 #180 ]', 
), )
a.Surface(side1Faces=side1Faces1, name='CP-28-b')
region55=a.surfaces['CP-28-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt23'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-28-bfbolt23')
region56=a.surfaces['CP-28-bfbolt23']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['ep'].faces
side2Faces1 = s1.getSequenceFromMask(mask=('[#100fc0 ]', ), )
a.Surface(side2Faces=side2Faces1, name='CP-29-ep')
region57=a.surfaces['CP-29-ep']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #40000000 #3e2 #20 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-29-kpl-d')
region58=a.surfaces['CP-29-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4000000 #80 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-30-kpl-d')
region59=a.surfaces['CP-30-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt12'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-30-bfbolt12')
region60=a.surfaces['CP-30-bfbolt12']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#2000000 #80 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-31-kpl-d')
region61=a.surfaces['CP-31-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt13'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-31-bfbolt13')
region62=a.surfaces['CP-31-bfbolt13']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#8000000 #80 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-32-kpl-d')
region63=a.surfaces['CP-32-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt14'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-32-bfbolt14')
region64=a.surfaces['CP-32-bfbolt14']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1000000 #80 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-33-kpl-d')
region65=a.surfaces['CP-33-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt15'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-33-bfbolt15')
region66=a.surfaces['CP-33-bfbolt15']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#10000000 #80 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-34-kpl-d')
region67=a.surfaces['CP-34-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt16'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-34-bfbolt16')
region68=a.surfaces['CP-34-bfbolt16']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#800000 #80 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-35-kpl-d')
region69=a.surfaces['CP-35-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt17'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-35-bfbolt17')
region70=a.surfaces['CP-35-bfbolt17']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #1400 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-36-kpl-d')
region71=a.surfaces['CP-36-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt06'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-36-cwbolt06')
region72=a.surfaces['CP-36-cwbolt06']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #4400 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-37-kpl-d')
region73=a.surfaces['CP-37-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt07'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-37-cwbolt07')
region74=a.surfaces['CP-37-cwbolt07']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #10400 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-38-kpl-d')
region75=a.surfaces['CP-38-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt08'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-38-cwbolt08')
region76=a.surfaces['CP-38-cwbolt08']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #40400 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-39-kpl-d')
region77=a.surfaces['CP-39-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt09'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-39-cwbolt09')
region78=a.surfaces['CP-39-cwbolt09']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #100400 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-40-kpl-d')
region79=a.surfaces['CP-40-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt10'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-40-cwbolt10')
region80=a.surfaces['CP-40-cwbolt10']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #400 #0 #40 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-41-kpl-d')
region81=a.surfaces['CP-41-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt11'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-41-cwbolt11')
region82=a.surfaces['CP-41-cwbolt11']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['ep'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#100fc0 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-42-ep')
region83=a.surfaces['CP-42-ep']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #155400 #1 #40 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-42-kpr-d')
region84=a.surfaces['CP-42-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#80 #100 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-43-kpr-d')
region85=a.surfaces['CP-43-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt18'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-43-bfbolt18')
region86=a.surfaces['CP-43-bfbolt18']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#400080 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-44-kpr-d')
region87=a.surfaces['CP-44-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt19'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-44-bfbolt19')
region88=a.surfaces['CP-44-bfbolt19']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#100080 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-45-kpr-d')
region89=a.surfaces['CP-45-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt20'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-45-bfbolt20')
region90=a.surfaces['CP-45-bfbolt20']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#40080 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-46-kpr-d')
region91=a.surfaces['CP-46-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt21'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-46-bfbolt21')
region92=a.surfaces['CP-46-bfbolt21']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#10080 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-47-kpr-d')
region93=a.surfaces['CP-47-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt22'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-47-bfbolt22')
region94=a.surfaces['CP-47-bfbolt22']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4080 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-48-kpr-d')
region95=a.surfaces['CP-48-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt23'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-48-bfbolt23')
region96=a.surfaces['CP-48-bfbolt23']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0:2 #22 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-49-kpr-d')
region97=a.surfaces['CP-49-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt06'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-49-cwbolt06')
region98=a.surfaces['CP-49-cwbolt06']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0:2 #42 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-50-kpr-d')
region99=a.surfaces['CP-50-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt07'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-50-cwbolt07')
region100=a.surfaces['CP-50-cwbolt07']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0:2 #82 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-51-kpr-d')
region101=a.surfaces['CP-51-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt08'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-51-cwbolt08')
region102=a.surfaces['CP-51-cwbolt08']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0:2 #2 #20 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-52-kpr-d')
region103=a.surfaces['CP-52-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt09'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-52-cwbolt09')
region104=a.surfaces['CP-52-cwbolt09']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0:2 #202 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-53-kpr-d')
region105=a.surfaces['CP-53-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt10'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-53-cwbolt10')
region106=a.surfaces['CP-53-cwbolt10']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0:2 #102 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-54-kpr-d')
region107=a.surfaces['CP-54-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt11'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-54-cwbolt11')
region108=a.surfaces['CP-54-cwbolt11']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['ep'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#403f ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-55-ep')
region109=a.surfaces['CP-55-ep']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #40000000 #3e2 #20 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-55-kpl-u')
region110=a.surfaces['CP-55-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4000000 #80 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-56-kpl-u')
region111=a.surfaces['CP-56-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt06'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-56-bfbolt06')
region112=a.surfaces['CP-56-bfbolt06']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#2000000 #80 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-57-kpl-u')
region113=a.surfaces['CP-57-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt07'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-57-bfbolt07')
region114=a.surfaces['CP-57-bfbolt07']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#8000000 #80 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-58-kpl-u')
region115=a.surfaces['CP-58-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt08'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-58-bfbolt08')
region116=a.surfaces['CP-58-bfbolt08']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1000000 #80 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-59-kpl-u')
region117=a.surfaces['CP-59-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt09'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-59-bfbolt09')
region118=a.surfaces['CP-59-bfbolt09']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#10000000 #80 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-60-kpl-u')
region119=a.surfaces['CP-60-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt10'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-60-bfbolt10')
region120=a.surfaces['CP-60-bfbolt10']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#800000 #80 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-61-kpl-u')
region121=a.surfaces['CP-61-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt11'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-61-bfbolt11')
region122=a.surfaces['CP-61-bfbolt11']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #40400 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-62-kpl-u')
region123=a.surfaces['CP-62-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt00'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-62-cwbolt00')
region124=a.surfaces['CP-62-cwbolt00']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #100400 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-63-kpl-u')
region125=a.surfaces['CP-63-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt01'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-63-cwbolt01')
region126=a.surfaces['CP-63-cwbolt01']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #400 #0 #40 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-64-kpl-u')
region127=a.surfaces['CP-64-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt02'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-64-cwbolt02')
region128=a.surfaces['CP-64-cwbolt02']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #1400 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-65-kpl-u')
region129=a.surfaces['CP-65-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt03'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-65-cwbolt03')
region130=a.surfaces['CP-65-cwbolt03']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #4400 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-66-kpl-u')
region131=a.surfaces['CP-66-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt04'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-66-cwbolt04')
region132=a.surfaces['CP-66-cwbolt04']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #10400 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-67-kpl-u')
region133=a.surfaces['CP-67-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt05'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-67-cwbolt05')
region134=a.surfaces['CP-67-cwbolt05']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['ep'].faces
side2Faces1 = s1.getSequenceFromMask(mask=('[#403f ]', ), )
a.Surface(side2Faces=side2Faces1, name='CP-68-ep')
region135=a.surfaces['CP-68-ep']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #155400 #1 #40 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-68-kpr-u')
region136=a.surfaces['CP-68-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#80 #100 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-69-kpr-u')
region137=a.surfaces['CP-69-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt00'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-69-bfbolt00')
region138=a.surfaces['CP-69-bfbolt00']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#400080 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-70-kpr-u')
region139=a.surfaces['CP-70-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt01'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-70-bfbolt01')
region140=a.surfaces['CP-70-bfbolt01']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#100080 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-71-kpr-u')
region141=a.surfaces['CP-71-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt02'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-71-bfbolt02')
region142=a.surfaces['CP-71-bfbolt02']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#40080 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-72-kpr-u')
region143=a.surfaces['CP-72-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt03'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-72-bfbolt03')
region144=a.surfaces['CP-72-bfbolt03']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#10080 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-73-kpr-u')
region145=a.surfaces['CP-73-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt04'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-73-bfbolt04')
region146=a.surfaces['CP-73-bfbolt04']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4080 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-74-kpr-u')
region147=a.surfaces['CP-74-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt05'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-74-bfbolt05')
region148=a.surfaces['CP-74-bfbolt05']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0:2 #2 #20 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-75-kpr-u')
region149=a.surfaces['CP-75-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt00'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-75-cwbolt00')
region150=a.surfaces['CP-75-cwbolt00']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0:2 #202 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-76-kpr-u')
region151=a.surfaces['CP-76-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt01'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-76-cwbolt01')
region152=a.surfaces['CP-76-cwbolt01']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0:2 #102 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-77-kpr-u')
region153=a.surfaces['CP-77-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt02'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-77-cwbolt02')
region154=a.surfaces['CP-77-cwbolt02']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0:2 #22 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-78-kpr-u')
region155=a.surfaces['CP-78-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt03'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-78-cwbolt03')
region156=a.surfaces['CP-78-cwbolt03']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0:2 #42 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-79-kpr-u')
region157=a.surfaces['CP-79-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt04'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-79-cwbolt04')
region158=a.surfaces['CP-79-cwbolt04']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0:2 #82 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-80-kpr-u')
region159=a.surfaces['CP-80-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt05'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-80-cwbolt05')
region160=a.surfaces['CP-80-cwbolt05']
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-1-b-kpl-d', 
createStepName='Initial', master=region1, slave=region2, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-2-b-kpr-d', 
createStepName='Initial', master=region3, slave=region4, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-3-b-kpl-u', 
createStepName='Initial', master=region5, slave=region6, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-4-b-kpr-u', 
createStepName='Initial', master=region7, slave=region8, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-5-b-bfbolt00', 
createStepName='Initial', master=region9, slave=region10, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-6-b-bfbolt01', 
createStepName='Initial', master=region11, slave=region12, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-7-b-bfbolt02', 
createStepName='Initial', master=region13, slave=region14, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-8-b-bfbolt03', 
createStepName='Initial', master=region15, slave=region16, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-9-b-bfbolt04', 
createStepName='Initial', master=region17, slave=region18, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-10-b-bfbolt05', 
createStepName='Initial', master=region19, slave=region20, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-11-b-bfbolt06', 
createStepName='Initial', master=region21, slave=region22, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-12-b-bfbolt07', 
createStepName='Initial', master=region23, slave=region24, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-13-b-bfbolt08', 
createStepName='Initial', master=region25, slave=region26, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-14-b-bfbolt09', 
createStepName='Initial', master=region27, slave=region28, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-15-b-bfbolt10', 
createStepName='Initial', master=region29, slave=region30, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-16-b-bfbolt11', 
createStepName='Initial', master=region31, slave=region32, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-17-b-bfbolt12', 
createStepName='Initial', master=region33, slave=region34, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-18-b-bfbolt13', 
createStepName='Initial', master=region35, slave=region36, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-19-b-bfbolt14', 
createStepName='Initial', master=region37, slave=region38, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-20-b-bfbolt15', 
createStepName='Initial', master=region39, slave=region40, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-21-b-bfbolt16', 
createStepName='Initial', master=region41, slave=region42, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-22-b-bfbolt17', 
createStepName='Initial', master=region43, slave=region44, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-23-b-bfbolt18', 
createStepName='Initial', master=region45, slave=region46, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-24-b-bfbolt19', 
createStepName='Initial', master=region47, slave=region48, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-25-b-bfbolt20', 
createStepName='Initial', master=region49, slave=region50, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-26-b-bfbolt21', 
createStepName='Initial', master=region51, slave=region52, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-27-b-bfbolt22', 
createStepName='Initial', master=region53, slave=region54, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-28-b-bfbolt23', 
createStepName='Initial', master=region55, slave=region56, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-29-ep-kpl-d', 
createStepName='Initial', master=region57, slave=region58, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-30-kpl-d-bfbolt12', 
createStepName='Initial', master=region59, slave=region60, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-31-kpl-d-bfbolt13', 
createStepName='Initial', master=region61, slave=region62, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-32-kpl-d-bfbolt14', 
createStepName='Initial', master=region63, slave=region64, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-33-kpl-d-bfbolt15', 
createStepName='Initial', master=region65, slave=region66, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-34-kpl-d-bfbolt16', 
createStepName='Initial', master=region67, slave=region68, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-35-kpl-d-bfbolt17', 
createStepName='Initial', master=region69, slave=region70, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-36-kpl-d-cwbolt06', 
createStepName='Initial', master=region71, slave=region72, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-37-kpl-d-cwbolt07', 
createStepName='Initial', master=region73, slave=region74, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-38-kpl-d-cwbolt08', 
createStepName='Initial', master=region75, slave=region76, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-39-kpl-d-cwbolt09', 
createStepName='Initial', master=region77, slave=region78, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-40-kpl-d-cwbolt10', 
createStepName='Initial', master=region79, slave=region80, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-41-kpl-d-cwbolt11', 
createStepName='Initial', master=region81, slave=region82, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-42-ep-kpr-d', 
createStepName='Initial', master=region83, slave=region84, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-43-kpr-d-bfbolt18', 
createStepName='Initial', master=region85, slave=region86, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-44-kpr-d-bfbolt19', 
createStepName='Initial', master=region87, slave=region88, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-45-kpr-d-bfbolt20', 
createStepName='Initial', master=region89, slave=region90, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-46-kpr-d-bfbolt21', 
createStepName='Initial', master=region91, slave=region92, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-47-kpr-d-bfbolt22', 
createStepName='Initial', master=region93, slave=region94, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-48-kpr-d-bfbolt23', 
createStepName='Initial', master=region95, slave=region96, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-49-kpr-d-cwbolt06', 
createStepName='Initial', master=region97, slave=region98, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-50-kpr-d-cwbolt07', 
createStepName='Initial', master=region99, slave=region100, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-51-kpr-d-cwbolt08', 
createStepName='Initial', master=region101, slave=region102, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-52-kpr-d-cwbolt09', 
createStepName='Initial', master=region103, slave=region104, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-53-kpr-d-cwbolt10', 
createStepName='Initial', master=region105, slave=region106, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-54-kpr-d-cwbolt11', 
createStepName='Initial', master=region107, slave=region108, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-55-ep-kpl-u', 
createStepName='Initial', master=region109, slave=region110, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-56-kpl-u-bfbolt06', 
createStepName='Initial', master=region111, slave=region112, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-57-kpl-u-bfbolt07', 
createStepName='Initial', master=region113, slave=region114, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-58-kpl-u-bfbolt08', 
createStepName='Initial', master=region115, slave=region116, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-59-kpl-u-bfbolt09', 
createStepName='Initial', master=region117, slave=region118, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-60-kpl-u-bfbolt10', 
createStepName='Initial', master=region119, slave=region120, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-61-kpl-u-bfbolt11', 
createStepName='Initial', master=region121, slave=region122, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-62-kpl-u-cwbolt00', 
createStepName='Initial', master=region123, slave=region124, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-63-kpl-u-cwbolt01', 
createStepName='Initial', master=region125, slave=region126, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-64-kpl-u-cwbolt02', 
createStepName='Initial', master=region127, slave=region128, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-65-kpl-u-cwbolt03', 
createStepName='Initial', master=region129, slave=region130, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-66-kpl-u-cwbolt04', 
createStepName='Initial', master=region131, slave=region132, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-67-kpl-u-cwbolt05', 
createStepName='Initial', master=region133, slave=region134, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-68-ep-kpr-u', 
createStepName='Initial', master=region135, slave=region136, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-69-kpr-u-bfbolt00', 
createStepName='Initial', master=region137, slave=region138, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-70-kpr-u-bfbolt01', 
createStepName='Initial', master=region139, slave=region140, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-71-kpr-u-bfbolt02', 
createStepName='Initial', master=region141, slave=region142, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-72-kpr-u-bfbolt03', 
createStepName='Initial', master=region143, slave=region144, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-73-kpr-u-bfbolt04', 
createStepName='Initial', master=region145, slave=region146, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-74-kpr-u-bfbolt05', 
createStepName='Initial', master=region147, slave=region148, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-75-kpr-u-cwbolt00', 
createStepName='Initial', master=region149, slave=region150, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-76-kpr-u-cwbolt01', 
createStepName='Initial', master=region151, slave=region152, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-77-kpr-u-cwbolt02', 
createStepName='Initial', master=region153, slave=region154, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-78-kpr-u-cwbolt03', 
createStepName='Initial', master=region155, slave=region156, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-79-kpr-u-cwbolt04', 
createStepName='Initial', master=region157, slave=region158, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-80-kpr-u-cwbolt05', 
createStepName='Initial', master=region159, slave=region160, 
sliding=FINITE, interactionProperty='IntProp-1')



p = mdb.models['Model-1'].parts['n22l']
session.viewports['Viewport: 1'].setValues(displayedObject=p)
mdb.models['Model-1'].parts['n22l'].sectionAssignments[0].setValues(
sectionName='ss400flat')

p = mdb.models['Model-1'].parts['n22r']
session.viewports['Viewport: 1'].setValues(displayedObject=p)
mdb.models['Model-1'].parts['n22r'].sectionAssignments[0].setValues(
sectionName='ss400flat')


model="Model-1"


#F14TおよびF10T→質量を16倍にした(マススケーリング法)
#同様に、接合金物(490k,400k)も質量を16倍にしている。
mdb.models[model].Material(name='F14T')
mdb.models[model].materials['F14T'].Density(table=((62.4e-05, ), ))
mdb.models[model].materials['F14T'].Elastic(table=((205000.0, 0.3), 
))
mdb.models[model].materials['F14T'].Plastic(table=((1260.0, 0.0), 
(2400.0, 1.0)))

mdb.models[model].Material(name='F10T')
mdb.models[model].materials['F10T'].Density(table=((62.4e-05, ), ))
mdb.models[model].materials['F10T'].Elastic(table=((205000.0, 0.3), 
))
mdb.models[model].materials['F10T'].Plastic(table=((900.0, 0.0), 
(1700.0, 1.0)))

mdb.models[model].HomogeneousSolidSection(name='F14T', material='F14T', thickness=None)
mdb.models[model].HomogeneousSolidSection(name='F10T', material='F10T', thickness=None)


mdb.models[model].Material(name='steel')
mdb.models[model].materials['steel'].Density(table=((62.4e-05, ), ))
mdb.models[model].materials['steel'].Elastic(table=((205000.0, 0.3), ))
mdb.models[model].HomogeneousSolidSection(name='steel', material='steel', thickness=None)


mdb.models[model].Material(name='sm490flat')
mdb.models[model].materials['sm490flat'].Density(table=((62.4e-05, ), ))
mdb.models[model].materials['sm490flat'].Elastic(table=((205000.0, 0.3), ))
mdb.models[model].materials['sm490flat'].Plastic(table=(
(325.0, 0.0), 
(326.0, 1.0)))
mdb.models[model].HomogeneousSolidSection(name='sm490flat', material='sm490flat', thickness=None)




mdb.models[model].Material(name='sm490')
mdb.models[model].materials['sm490'].Density(table=((62.4e-05, ), ))
mdb.models[model].materials['sm490'].Elastic(table=((205000.0, 0.3), ))
mdb.models[model].materials['sm490'].Plastic(table=(
(325.0, 0.0), 
(325.0, 0.02), 
(371.31, 0.03),
 (404.17, 0.04), 
 (429.66, 0.05), 
 (450.48, 0.0584), 
 (468.09, 0.07), 
 (483.34, 0.08), 
 (496.79, 0.09), 
 (508.83, 0.1), 
(519.72, 0.11), 
(529.65, 0.12), 
(538.8, 0.13), 
(547.26, 0.14), 
(555.14, 0.15), 
(562.51, 0.16), 
(569.44, 0.17), 
(575.97, 0.18), 
(582.14, 0.19), 
(588.0, 0.2), 
(589.0, 1.0)))

mdb.models[model].HomogeneousSolidSection(name='sm490', material='sm490', thickness=None)



mdb.models[model].Material(name='ss400flat')
mdb.models[model].materials['ss400flat'].Density(table=((62.4e-05, ), ))
mdb.models[model].materials['ss400flat'].Elastic(table=((205000.0, 0.3), ))
mdb.models[model].materials['ss400flat'].Plastic(table=(
(235.0, 0.0), 
(236.0, 1.0)))
mdb.models[model].HomogeneousSolidSection(name='ss400flat', material='ss400flat', thickness=None)



mdb.models[model].Material(name='ss400')
mdb.models[model].materials['ss400'].Elastic(table=((205000.0, 0.3), ))
mdb.models[model].materials['ss400'].Plastic(table=(
(235.0, 0.0), 
(235.0, 0.02), 
(332.49, 0.05), 
(382.5, 0.08), 
(416.39, 0.11), 
(442.05, 0.14), 
(462.71, 0.17), 
(480.0, 0.2), 
(481.0, 1.0)
))
mdb.models[model].materials['ss400'].Density(table=((62.4e-05, ), ))

mdb.models[model].HomogeneousSolidSection(name='ss400', material='ss400', thickness=None)



mdb.models[model].steps['Step-1'].setValues(timePeriod=0.5)
mdb.models[model].steps['Step-2'].setValues(timePeriod=10.0)
mdb.models[model].fieldOutputRequests['F-Output-2'].setValues(
timeInterval=0.1)
mdb.models[model].fieldOutputRequests['F-Output-3'].setValues(
timeInterval=0.1)
mdb.models[model].amplitudes['p1'].setValues(timeSpan=STEP, data=((0.0, 
0.0), (0.25, 1.0), (0.5, 1.0)))
mdb.models[model].amplitudes['p2'].setValues(timeSpan=STEP, data=((0.0, 
0.0), (0.25, 0.0), (0.5, 1.0)))
mdb.models[model].amplitudes['main'].setValues(timeSpan=STEP, data=((0.0, 
0.0), (10.0, 1.0)))


a = mdb.models['Model-1'].rootAssembly
c1 = a.instances['b'].cells
cells1 = c1.getSequenceFromMask(mask=('[#2040 #20000000 #2000200 #4 ]', ), )
c2 = a.instances['kpl-d'].cells
cells2 = c2.getSequenceFromMask(mask=('[#800 ]', ), )
e2 = a.instances['kpl-d'].edges
edges2 = e2.getSequenceFromMask(mask=('[#40000 #0:2 #c000 ]', ), )
c3 = a.instances['kpr-d'].cells
cells3 = c3.getSequenceFromMask(mask=('[#800 ]', ), )
e3 = a.instances['kpr-d'].edges
edges3 = e3.getSequenceFromMask(mask=('[#0:2 #1000 #6000 ]', ), )
c4 = a.instances['kpl-u'].cells
cells4 = c4.getSequenceFromMask(mask=('[#800 ]', ), )
e4 = a.instances['kpl-u'].edges
edges4 = e4.getSequenceFromMask(mask=('[#40000 #0:2 #c000 ]', ), )
c5 = a.instances['kpr-u'].cells
cells5 = c5.getSequenceFromMask(mask=('[#800 ]', ), )
e5 = a.instances['kpr-u'].edges
edges5 = e5.getSequenceFromMask(mask=('[#0:2 #1000 #6000 ]', ), )
a.Set(edges=edges2+edges3+edges4+edges5, cells=cells1+cells2+cells3+cells4+\
cells5, name='outputelement')



