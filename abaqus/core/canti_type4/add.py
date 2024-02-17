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



mdb.models['Model-1'].parts['n16l'].sectionAssignments[0].setValues(
sectionName='ss400flat')
p = mdb.models['Model-1'].parts['n16r']
session.viewports['Viewport: 1'].setValues(displayedObject=p)
mdb.models['Model-1'].parts['n16r'].sectionAssignments[0].setValues(
sectionName='ss400flat')



a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#400000aa #4 #200000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-1-kpl-d')
region1=a.surfaces['CP-1-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#0 #1f0 #0 #40100000 #310 #0 #80000000', ' #8 #0:3 #aa0 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-1-b')
region2=a.surfaces['CP-1-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#400000aa #4 #200000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-2-kpr-d')
region3=a.surfaces['CP-2-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#0:2 #3e00 #0:2 #8080000 #80310000 #0:4 #1550 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-2-b')
region4=a.surfaces['CP-2-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#400000aa #4 #200000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-3-kpl-u')
region5=a.surfaces['CP-3-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#0 #3e000000 #0:3 #800000 #2209 #840 #4210', ' #0:2 #8 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-3-b')
region6=a.surfaces['CP-3-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#400000aa #4 #200000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-4-kpr-u')
region7=a.surfaces['CP-4-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#1f00000 #0:2 #2010000 #c4000 #0 #8000000 #82210800 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-4-b')
region8=a.surfaces['CP-4-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#4c000 #0:3 #12000 #0:5 #40000000 ]', ), )
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
'[#58000 #0:3 #110000 #0:2 #4000000 ]', ), )
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
'[#70000 #0:2 #4000000 #10000 #0:2 #400000 ]', ), )
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
'[#60000 #0:2 #20000 #10000 #0:2 #21000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-8-b')
region15=a.surfaces['CP-8-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt03'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-8-bfbolt03')
region16=a.surfaces['CP-8-bfbolt03']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0:2 #6 #0:3 #900 #0 #21 ]', ), )
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
'[#0:2 #7 #0:2 #80000000 #0:2 #401 ]', ), )
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
'[#0 #80000000 #5 #0:3 #10 #0 #8001 ]', ), )
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
'[#0 #c0000000 #4 #0:2 #1000000 #0:2 #1', ' #0:3 #2000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-12-b')
region23=a.surfaces['CP-12-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt07'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-12-bfbolt07')
region24=a.surfaces['CP-12-bfbolt07']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #6 #0 #200000 #40 #0:2 #400', 
' #0:2 #8000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-13-b')
region25=a.surfaces['CP-13-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt08'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-13-bfbolt08')
region26=a.surfaces['CP-13-bfbolt08']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #7 #0:2 #400 #0:2 #8000400 ]', 
), )
a.Surface(side1Faces=side1Faces1, name='CP-14-b')
region27=a.surfaces['CP-14-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt09'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-14-bfbolt09')
region28=a.surfaces['CP-14-bfbolt09']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#80000000 #5 #0:2 #8 #0:2 #100400 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-15-b')
region29=a.surfaces['CP-15-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt10'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-15-bfbolt10')
region30=a.surfaces['CP-15-bfbolt10']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#c0000000 #4 #0 #20000000 #0:3 #8400 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-16-b')
region31=a.surfaces['CP-16-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt11'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-16-bfbolt11')
region32=a.surfaces['CP-16-bfbolt11']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['b'].faces
side1Faces1 = s1.getSequenceFromMask(mask=(
'[#0:2 #4c000 #0:2 #100000 #0 #40000000 #8 ]', ), )
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
'[#0:2 #58000 #0:2 #10000000 #0 #40000000 #100 ]', ), )
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
'[#0:2 #70000 #0:3 #400000 #40000000 #2000 ]', ), )
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
'[#0:2 #60000 #0:3 #44000 #40000000 #0:4 #10000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-20-b')
region39=a.surfaces['CP-20-b']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt15'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-20-bfbolt15')
region40=a.surfaces['CP-20-bfbolt15']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['ep'].faces
side2Faces1 = s1.getSequenceFromMask(mask=('[#100fc0 ]', ), )
a.Surface(side2Faces=side2Faces1, name='CP-21-ep')
region41=a.surfaces['CP-21-ep']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1000aa00 #400 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-21-kpl-d')
region42=a.surfaces['CP-21-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #60000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-22-kpl-d')
region43=a.surfaces['CP-22-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt08'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-22-bfbolt08')
region44=a.surfaces['CP-22-bfbolt08']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #50000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-23-kpl-d')
region45=a.surfaces['CP-23-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt09'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-23-bfbolt09')
region46=a.surfaces['CP-23-bfbolt09']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #40000 #1000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-24-kpl-d')
region47=a.surfaces['CP-24-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt10'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-24-bfbolt10')
region48=a.surfaces['CP-24-bfbolt10']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #48000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-25-kpl-d')
region49=a.surfaces['CP-25-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt11'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-25-bfbolt11')
region50=a.surfaces['CP-25-bfbolt11']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#8000000 #800 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-26-kpl-d')
region51=a.surfaces['CP-26-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt04'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-26-cwbolt04')
region52=a.surfaces['CP-26-cwbolt04']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#8000000 #0 #400 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-27-kpl-d')
region53=a.surfaces['CP-27-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt05'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-27-cwbolt05')
region54=a.surfaces['CP-27-cwbolt05']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#8000000 #1000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-28-kpl-d')
region55=a.surfaces['CP-28-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt06'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-28-cwbolt06')
region56=a.surfaces['CP-28-cwbolt06']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#8000000 #2000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-29-kpl-d')
region57=a.surfaces['CP-29-kpl-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt07'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-29-cwbolt07')
region58=a.surfaces['CP-29-cwbolt07']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['ep'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#100fc0 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-30-ep')
region59=a.surfaces['CP-30-ep']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1000aa00 #400 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-30-kpr-d')
region60=a.surfaces['CP-30-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #60000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-31-kpr-d')
region61=a.surfaces['CP-31-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt12'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-31-bfbolt12')
region62=a.surfaces['CP-31-bfbolt12']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #50000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-32-kpr-d')
region63=a.surfaces['CP-32-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt13'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-32-bfbolt13')
region64=a.surfaces['CP-32-bfbolt13']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #40000 #1000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-33-kpr-d')
region65=a.surfaces['CP-33-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt14'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-33-bfbolt14')
region66=a.surfaces['CP-33-bfbolt14']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #48000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-34-kpr-d')
region67=a.surfaces['CP-34-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt15'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-34-bfbolt15')
region68=a.surfaces['CP-34-bfbolt15']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#8000000 #800 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-35-kpr-d')
region69=a.surfaces['CP-35-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt04'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-35-cwbolt04')
region70=a.surfaces['CP-35-cwbolt04']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#8000000 #0 #400 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-36-kpr-d')
region71=a.surfaces['CP-36-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt05'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-36-cwbolt05')
region72=a.surfaces['CP-36-cwbolt05']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#8000000 #1000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-37-kpr-d')
region73=a.surfaces['CP-37-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt06'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-37-cwbolt06')
region74=a.surfaces['CP-37-cwbolt06']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-d'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#8000000 #2000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-38-kpr-d')
region75=a.surfaces['CP-38-kpr-d']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt07'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-38-cwbolt07')
region76=a.surfaces['CP-38-cwbolt07']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['ep'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#403f ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-39-ep')
region77=a.surfaces['CP-39-ep']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1000aa00 #400 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-39-kpl-u')
region78=a.surfaces['CP-39-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #60000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-40-kpl-u')
region79=a.surfaces['CP-40-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt04'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-40-bfbolt04')
region80=a.surfaces['CP-40-bfbolt04']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #50000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-41-kpl-u')
region81=a.surfaces['CP-41-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt05'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-41-bfbolt05')
region82=a.surfaces['CP-41-bfbolt05']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #40000 #1000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-42-kpl-u')
region83=a.surfaces['CP-42-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt06'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-42-bfbolt06')
region84=a.surfaces['CP-42-bfbolt06']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #48000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-43-kpl-u')
region85=a.surfaces['CP-43-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt07'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-43-bfbolt07')
region86=a.surfaces['CP-43-bfbolt07']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#8000000 #1000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-44-kpl-u')
region87=a.surfaces['CP-44-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt00'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-44-cwbolt00')
region88=a.surfaces['CP-44-cwbolt00']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#8000000 #2000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-45-kpl-u')
region89=a.surfaces['CP-45-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt01'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-45-cwbolt01')
region90=a.surfaces['CP-45-cwbolt01']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#8000000 #800 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-46-kpl-u')
region91=a.surfaces['CP-46-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt02'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-46-cwbolt02')
region92=a.surfaces['CP-46-cwbolt02']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpl-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#8000000 #0 #400 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-47-kpl-u')
region93=a.surfaces['CP-47-kpl-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt03'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-47-cwbolt03')
region94=a.surfaces['CP-47-cwbolt03']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['ep'].faces
side2Faces1 = s1.getSequenceFromMask(mask=('[#403f ]', ), )
a.Surface(side2Faces=side2Faces1, name='CP-48-ep')
region95=a.surfaces['CP-48-ep']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1000aa00 #400 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-48-kpr-u')
region96=a.surfaces['CP-48-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #60000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-49-kpr-u')
region97=a.surfaces['CP-49-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt00'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-49-bfbolt00')
region98=a.surfaces['CP-49-bfbolt00']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #50000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-50-kpr-u')
region99=a.surfaces['CP-50-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt01'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-50-bfbolt01')
region100=a.surfaces['CP-50-bfbolt01']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #40000 #1000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-51-kpr-u')
region101=a.surfaces['CP-51-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt02'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-51-bfbolt02')
region102=a.surfaces['CP-51-bfbolt02']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#0 #48000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-52-kpr-u')
region103=a.surfaces['CP-52-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt03'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#1010000 #208 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-52-bfbolt03')
region104=a.surfaces['CP-52-bfbolt03']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#8000000 #1000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-53-kpr-u')
region105=a.surfaces['CP-53-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt00'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-53-cwbolt00')
region106=a.surfaces['CP-53-cwbolt00']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#8000000 #2000 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-54-kpr-u')
region107=a.surfaces['CP-54-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt01'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-54-cwbolt01')
region108=a.surfaces['CP-54-cwbolt01']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#8000000 #800 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-55-kpr-u')
region109=a.surfaces['CP-55-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt02'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-55-cwbolt02')
region110=a.surfaces['CP-55-cwbolt02']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['kpr-u'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#8000000 #0 #400 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-56-kpr-u')
region111=a.surfaces['CP-56-kpr-u']
a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt03'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#4100 #4004 ]', ), )
a.Surface(side1Faces=side1Faces1, name='CP-56-cwbolt03')
region112=a.surfaces['CP-56-cwbolt03']
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-1-kpl-d-b', 
createStepName='Initial', master=region1, slave=region2, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-2-kpr-d-b', 
createStepName='Initial', master=region3, slave=region4, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-3-kpl-u-b', 
createStepName='Initial', master=region5, slave=region6, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-4-kpr-u-b', 
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
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-21-ep-kpl-d', 
createStepName='Initial', master=region41, slave=region42, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-22-kpl-d-bfbolt08', 
createStepName='Initial', master=region43, slave=region44, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-23-kpl-d-bfbolt09', 
createStepName='Initial', master=region45, slave=region46, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-24-kpl-d-bfbolt10', 
createStepName='Initial', master=region47, slave=region48, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-25-kpl-d-bfbolt11', 
createStepName='Initial', master=region49, slave=region50, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-26-kpl-d-cwbolt04', 
createStepName='Initial', master=region51, slave=region52, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-27-kpl-d-cwbolt05', 
createStepName='Initial', master=region53, slave=region54, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-28-kpl-d-cwbolt06', 
createStepName='Initial', master=region55, slave=region56, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-29-kpl-d-cwbolt07', 
createStepName='Initial', master=region57, slave=region58, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-30-ep-kpr-d', 
createStepName='Initial', master=region59, slave=region60, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-31-kpr-d-bfbolt12', 
createStepName='Initial', master=region61, slave=region62, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-32-kpr-d-bfbolt13', 
createStepName='Initial', master=region63, slave=region64, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-33-kpr-d-bfbolt14', 
createStepName='Initial', master=region65, slave=region66, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-34-kpr-d-bfbolt15', 
createStepName='Initial', master=region67, slave=region68, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-35-kpr-d-cwbolt04', 
createStepName='Initial', master=region69, slave=region70, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-36-kpr-d-cwbolt05', 
createStepName='Initial', master=region71, slave=region72, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-37-kpr-d-cwbolt06', 
createStepName='Initial', master=region73, slave=region74, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-38-kpr-d-cwbolt07', 
createStepName='Initial', master=region75, slave=region76, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-39-ep-kpl-u', 
createStepName='Initial', master=region77, slave=region78, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-40-kpl-u-bfbolt04', 
createStepName='Initial', master=region79, slave=region80, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-41-kpl-u-bfbolt05', 
createStepName='Initial', master=region81, slave=region82, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-42-kpl-u-bfbolt06', 
createStepName='Initial', master=region83, slave=region84, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-43-kpl-u-bfbolt07', 
createStepName='Initial', master=region85, slave=region86, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-44-kpl-u-cwbolt00', 
createStepName='Initial', master=region87, slave=region88, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-45-kpl-u-cwbolt01', 
createStepName='Initial', master=region89, slave=region90, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-46-kpl-u-cwbolt02', 
createStepName='Initial', master=region91, slave=region92, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-47-kpl-u-cwbolt03', 
createStepName='Initial', master=region93, slave=region94, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-48-ep-kpr-u', 
createStepName='Initial', master=region95, slave=region96, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-49-kpr-u-bfbolt00', 
createStepName='Initial', master=region97, slave=region98, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-50-kpr-u-bfbolt01', 
createStepName='Initial', master=region99, slave=region100, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-51-kpr-u-bfbolt02', 
createStepName='Initial', master=region101, slave=region102, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-52-kpr-u-bfbolt03', 
createStepName='Initial', master=region103, slave=region104, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-53-kpr-u-cwbolt00', 
createStepName='Initial', master=region105, slave=region106, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-54-kpr-u-cwbolt01', 
createStepName='Initial', master=region107, slave=region108, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-55-kpr-u-cwbolt02', 
createStepName='Initial', master=region109, slave=region110, 
sliding=FINITE, interactionProperty='IntProp-1')
mdb.models['Model-1'].SurfaceToSurfaceContactExp(name='CP-56-kpr-u-cwbolt03', 
createStepName='Initial', master=region111, slave=region112, 
sliding=FINITE, interactionProperty='IntProp-1')







