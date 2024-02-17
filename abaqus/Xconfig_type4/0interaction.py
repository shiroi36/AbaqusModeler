
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
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4, name='b0l')

a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt04L'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['bfbolt05L'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['bfbolt06L'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['bfbolt07L'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4, name='b1l')

a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt08L'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['bfbolt09L'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['bfbolt10L'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['bfbolt11L'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4, name='b2l')

a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt12L'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['bfbolt13L'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['bfbolt14L'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['bfbolt15L'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4, name='b3l')


a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt00L'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['cwbolt01L'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['cwbolt02L'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['cwbolt03L'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4, name='b4l')

a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt04L'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['cwbolt05L'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['cwbolt06L'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['cwbolt07L'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4, name='b5l')

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
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5, name='b6l')

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
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4, name='b0r')

a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt04R'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['bfbolt05R'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['bfbolt06R'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['bfbolt07R'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4, name='b1r')

a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt08R'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['bfbolt09R'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['bfbolt10R'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['bfbolt11R'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4, name='b2r')

a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['bfbolt12R'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['bfbolt13R'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['bfbolt14R'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['bfbolt15R'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4, name='b3r')


a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt00R'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['cwbolt01R'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['cwbolt02R'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['cwbolt03R'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4, name='b4r')

a = mdb.models['Model-1'].rootAssembly
s1 = a.instances['cwbolt04R'].faces
side1Faces1 = s1.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s2 = a.instances['cwbolt05R'].faces
side1Faces2 = s2.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s3 = a.instances['cwbolt06R'].faces
side1Faces3 = s3.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
s4 = a.instances['cwbolt07R'].faces
side1Faces4 = s4.getSequenceFromMask(mask=('[#101000 #1010 ]', ), )
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4, name='b5r')

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
a.Surface(side1Faces=side1Faces1+side1Faces2+side1Faces3+side1Faces4+\
side1Faces5, name='b6r')


#------------------------------------------------------------------------------------
#ボルト圧縮領域サーフェスの作成


mdb.models['Model-1'].rootAssembly.Surface(name='p0', side1Faces=
    mdb.models['Model-1'].rootAssembly.instances['kpl-d0'].faces.getSequenceFromMask(
    mask=('[#0 #3800 #400 ]', ), )+\
    mdb.models['Model-1'].rootAssembly.instances['kpr-d0'].faces.getSequenceFromMask(
    mask=('[#0 #3800 #400 ]', ), )+\
    mdb.models['Model-1'].rootAssembly.instances['kpl-u0'].faces.getSequenceFromMask(
    mask=('[#0 #3800 #400 ]', ), )+\
    mdb.models['Model-1'].rootAssembly.instances['kpr-u0'].faces.getSequenceFromMask(
    mask=('[#0 #3800 #400 ]', ), )+\
    mdb.models['Model-1'].rootAssembly.instances['sp0'].faces.getSequenceFromMask(
    mask=('[#7c000 ]', ), )+\
    mdb.models['Model-1'].rootAssembly.instances['kpl-d1'].faces.getSequenceFromMask(
    mask=('[#0 #3800 #400 ]', ), )+\
    mdb.models['Model-1'].rootAssembly.instances['kpr-d1'].faces.getSequenceFromMask(
    mask=('[#0 #3800 #400 ]', ), )+\
    mdb.models['Model-1'].rootAssembly.instances['kpl-u1'].faces.getSequenceFromMask(
    mask=('[#0 #3800 #400 ]', ), )+\
    mdb.models['Model-1'].rootAssembly.instances['kpr-u1'].faces.getSequenceFromMask(
    mask=('[#0 #3800 #400 ]', ), )+\
    mdb.models['Model-1'].rootAssembly.instances['sp1'].faces.getSequenceFromMask(
    mask=('[#7c000 ]', ), )+\
    mdb.models['Model-1'].rootAssembly.instances['b1'].faces.getSequenceFromMask(
    mask=('[#7c0000 ]', ), )+\
    mdb.models['Model-1'].rootAssembly.instances['b0'].faces.getSequenceFromMask(
    mask=('[#7c0000 ]', ), ))
    

mdb.models['Model-1'].rootAssembly.Surface(name='p1', side1Faces=
    mdb.models['Model-1'].rootAssembly.instances['b0'].faces.getSequenceFromMask(
    mask=('[#0:4 #84400000 #2048104 #22000000 #8082212 #8a500000 #14a501', 
    ' #0 #100000 #4 #120000 ]', ), )+\
    mdb.models['Model-1'].rootAssembly.instances['kpl-d0'].faces.getSequenceFromMask(
    mask=('[#0 #38000 #1000 ]', ), )+\
    mdb.models['Model-1'].rootAssembly.instances['kpr-d0'].faces.getSequenceFromMask(
    mask=('[#0 #38000 #1000 ]', ), )+\
    mdb.models['Model-1'].rootAssembly.instances['kpl-u0'].faces.getSequenceFromMask(
    mask=('[#0 #38000 #1000 ]', ), )+\
    mdb.models['Model-1'].rootAssembly.instances['kpr-u0'].faces.getSequenceFromMask(
    mask=('[#0 #38000 #1000 ]', ), )+\
    mdb.models['Model-1'].rootAssembly.instances['b1'].faces.getSequenceFromMask(
    mask=('[#0:4 #84400000 #2048104 #22000000 #8082212 #8a500000 #14a501', 
    ' #0 #100000 #4 #120000 ]', ), )+\
    mdb.models['Model-1'].rootAssembly.instances['kpl-d1'].faces.getSequenceFromMask(
    mask=('[#0 #38000 #1000 ]', ), )+\
    mdb.models['Model-1'].rootAssembly.instances['kpr-d1'].faces.getSequenceFromMask(
    mask=('[#0 #38000 #1000 ]', ), )+\
    mdb.models['Model-1'].rootAssembly.instances['kpl-u1'].faces.getSequenceFromMask(
    mask=('[#0 #38000 #1000 ]', ), )+\
    mdb.models['Model-1'].rootAssembly.instances['kpr-u1'].faces.getSequenceFromMask(
    mask=('[#0 #38000 #1000 ]', ), ))
# Save by 14analysis on 2018_10_30-14.17.47; build 6.12-1 2012_03_13-20.23.18 119612


#------------------------------------------------------------------------------------
#結合の設定

mdb.models['Model-1'].rootAssembly.Surface(name='CP-1-col', side1Faces=
    mdb.models['Model-1'].rootAssembly.instances['col'].faces.getSequenceFromMask(
    ('[#0:4 #200 #0:6 #40 ]', ), ))
mdb.models['Model-1'].rootAssembly.Surface(name='CP-1-sp1', side1Faces=
    mdb.models['Model-1'].rootAssembly.instances['sp1'].faces.getSequenceFromMask(
    ('[#100000 ]', ), ))
mdb.models['Model-1'].rootAssembly.Surface(name='CP-2-col', side1Faces=
    mdb.models['Model-1'].rootAssembly.instances['col'].faces.getSequenceFromMask(
    ('[#0:3 #8 #0:15 #8 ]', ), ))
mdb.models['Model-1'].rootAssembly.Surface(name='CP-2-sp0', side1Faces=
    mdb.models['Model-1'].rootAssembly.instances['sp0'].faces.getSequenceFromMask(
    ('[#100000 ]', ), ))
mdb.models['Model-1'].Tie(adjust=ON, constraintEnforcement=SURFACE_TO_SURFACE, 
    master=mdb.models['Model-1'].rootAssembly.surfaces['CP-1-col'], name=
    'CP-1-col-sp1', positionToleranceMethod=COMPUTED, slave=
    mdb.models['Model-1'].rootAssembly.surfaces['CP-1-sp1'])
mdb.models['Model-1'].Tie(adjust=ON, constraintEnforcement=SURFACE_TO_SURFACE, 
    master=mdb.models['Model-1'].rootAssembly.surfaces['CP-2-col'], name=
    'CP-2-col-sp0', positionToleranceMethod=COMPUTED, slave=
    mdb.models['Model-1'].rootAssembly.surfaces['CP-2-sp0'])
mdb.models['Model-1'].constraints['CP-1-col-sp1'].setValues(adjust=OFF, 
    positionTolerance=20.0, positionToleranceMethod=SPECIFIED, thickness=OFF)
mdb.models['Model-1'].constraints['CP-2-col-sp0'].setValues(adjust=OFF, 
    positionTolerance=20.0, positionToleranceMethod=SPECIFIED, thickness=OFF)
# Save by 14analysis on 2018_10_30-14.49.33; build 6.12-1 2012_03_13-20.23.18 119612


mdb.models['Model-1'].rootAssembly.Surface(name='CP-3-col', side1Faces=
    mdb.models['Model-1'].rootAssembly.instances['col'].faces.getSequenceFromMask(
    ('[#4200 #410 #0 #400000 #24220 #4103000 #0:7', ' #8000 #0:3 #88200000 ]'), 
    ))
mdb.models['Model-1'].rootAssembly.Surface(name='CP-1-cfr1', side1Faces=
    mdb.models['Model-1'].rootAssembly.instances['cfr1'].faces.getSequenceFromMask(
    ('[#2000000 ]', ), ))
mdb.models['Model-1'].rootAssembly.Surface(name='CP-4-col', side1Faces=
    mdb.models['Model-1'].rootAssembly.instances['col'].faces.getSequenceFromMask(
    ('[#440 #4200 #1400000 #a #0:2 #10000 #11', 
    ' #0:4 #80000000 #1 #800 #0 #2000000 #0:2', ' #4 #0:6 #1 ]'), ))
mdb.models['Model-1'].rootAssembly.Surface(name='CP-2-cfr3', side1Faces=
    mdb.models['Model-1'].rootAssembly.instances['cfr3'].faces.getSequenceFromMask(
    ('[#2000000 ]', ), ))
mdb.models['Model-1'].rootAssembly.Surface(name='CP-5-col', side1Faces=
    mdb.models['Model-1'].rootAssembly.instances['col'].faces.getSequenceFromMask(
    ('[#8400000 #22000000 #0:7 #40200040 #a00000 #41 #0', 
    ' #400 #3 #0:9 #200800 #8000000 ]'), ))
mdb.models['Model-1'].rootAssembly.Surface(name='CP-3-cfr0', side1Faces=
    mdb.models['Model-1'].rootAssembly.instances['cfr0'].faces.getSequenceFromMask(
    ('[#2000000 ]', ), ))
mdb.models['Model-1'].rootAssembly.Surface(name='CP-6-col', side1Faces=
    mdb.models['Model-1'].rootAssembly.instances['col'].faces.getSequenceFromMask(
    ('[#20800000 #8200000 #0:6 #2206 #0:2 #10000000 #28500', 
    ' #0 #40 #1000 #0:3 #9 ]'), ))
mdb.models['Model-1'].rootAssembly.Surface(name='CP-4-cfr2', side1Faces=
    mdb.models['Model-1'].rootAssembly.instances['cfr2'].faces.getSequenceFromMask(
    ('[#2000000 ]', ), ))
mdb.models['Model-1'].Tie(adjust=ON, constraintEnforcement=SURFACE_TO_SURFACE, 
    master=mdb.models['Model-1'].rootAssembly.surfaces['CP-3-col'], name=
    'CP-1-col-cfr1', positionToleranceMethod=COMPUTED, slave=
    mdb.models['Model-1'].rootAssembly.surfaces['CP-1-cfr1'])
mdb.models['Model-1'].Tie(adjust=ON, constraintEnforcement=SURFACE_TO_SURFACE, 
    master=mdb.models['Model-1'].rootAssembly.surfaces['CP-4-col'], name=
    'CP-2-col-cfr3', positionToleranceMethod=COMPUTED, slave=
    mdb.models['Model-1'].rootAssembly.surfaces['CP-2-cfr3'])
mdb.models['Model-1'].Tie(adjust=ON, constraintEnforcement=SURFACE_TO_SURFACE, 
    master=mdb.models['Model-1'].rootAssembly.surfaces['CP-5-col'], name=
    'CP-3-col-cfr0', positionToleranceMethod=COMPUTED, slave=
    mdb.models['Model-1'].rootAssembly.surfaces['CP-3-cfr0'])
mdb.models['Model-1'].Tie(adjust=ON, constraintEnforcement=SURFACE_TO_SURFACE, 
    master=mdb.models['Model-1'].rootAssembly.surfaces['CP-6-col'], name=
    'CP-4-col-cfr2', positionToleranceMethod=COMPUTED, slave=
    mdb.models['Model-1'].rootAssembly.surfaces['CP-4-cfr2'])
mdb.models['Model-1'].constraints['CP-1-col-sp1'].setValues(adjust=OFF, 
    constraintEnforcement=SURFACE_TO_SURFACE, positionTolerance=20.0, 
    positionToleranceMethod=SPECIFIED)
mdb.models['Model-1'].constraints['CP-2-col-sp0'].setValues(adjust=OFF, 
    constraintEnforcement=SURFACE_TO_SURFACE, positionTolerance=20.0, 
    positionToleranceMethod=SPECIFIED)
mdb.models['Model-1'].constraints['CP-1-col-cfr1'].setValues(adjust=OFF, 
    positionTolerance=10.0, positionToleranceMethod=SPECIFIED, thickness=OFF)
mdb.models['Model-1'].constraints['CP-2-col-cfr3'].setValues(adjust=OFF, 
    positionTolerance=10.0, positionToleranceMethod=SPECIFIED, thickness=OFF)
mdb.models['Model-1'].constraints['CP-3-col-cfr0'].setValues(adjust=OFF, 
    positionTolerance=10.0, positionToleranceMethod=SPECIFIED, thickness=OFF)
mdb.models['Model-1'].constraints['CP-4-col-cfr2'].setValues(adjust=OFF, 
    positionTolerance=10.0, positionToleranceMethod=SPECIFIED, thickness=OFF)
mdb.models['Model-1'].constraints['CP-1-col-cfr1'].setValues(slave=
    mdb.models['Model-1'].rootAssembly.instances['cfr1'].sets['Set-1'])
mdb.models['Model-1'].constraints['CP-2-col-cfr3'].suppress()
mdb.models['Model-1'].constraints['CP-2-col-cfr3'].resume()
mdb.models['Model-1'].constraints['CP-2-col-cfr3'].setValues(slave=
    mdb.models['Model-1'].rootAssembly.instances['cfr3'].sets['Set-1'])
mdb.models['Model-1'].constraints['CP-3-col-cfr0'].setValues(slave=
    mdb.models['Model-1'].rootAssembly.instances['cfr0'].sets['Set-1'])
mdb.models['Model-1'].constraints['CP-4-col-cfr2'].setValues(slave=
    mdb.models['Model-1'].rootAssembly.instances['cfr2'].sets['Set-1'])
# Save by 14analysis on 2018_10_30-15.01.22; build 6.12-1 2012_03_13-20.23.18 119612


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

mdb.models['Model-1'].ContactExp(createStepName='Initial', name='Int-1')
mdb.models['Model-1'].interactions['Int-1'].includedPairs.setValuesInStep(
    addPairs=((mdb.models['Model-1'].rootAssembly.surfaces['b0l'], 
    mdb.models['Model-1'].rootAssembly.instances['b0'].surfaces['b3']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b0l'], 
    mdb.models['Model-1'].rootAssembly.instances['kpl-d0'].surfaces['b2']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b0r'], 
    mdb.models['Model-1'].rootAssembly.instances['b1'].surfaces['b3']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b0r'], 
    mdb.models['Model-1'].rootAssembly.instances['kpl-d1'].surfaces['b2']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b1l'], 
    mdb.models['Model-1'].rootAssembly.instances['b0'].surfaces['b4']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b1l'], 
    mdb.models['Model-1'].rootAssembly.instances['kpr-d0'].surfaces['b2']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b1r'], 
    mdb.models['Model-1'].rootAssembly.instances['b1'].surfaces['b4']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b1r'], 
    mdb.models['Model-1'].rootAssembly.instances['kpr-d1'].surfaces['b2']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b2l'], 
    mdb.models['Model-1'].rootAssembly.instances['b0'].surfaces['b2']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b2l'], 
    mdb.models['Model-1'].rootAssembly.instances['kpr-u0'].surfaces['b2']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b2r'], 
    mdb.models['Model-1'].rootAssembly.instances['b1'].surfaces['b2']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b2r'], 
    mdb.models['Model-1'].rootAssembly.instances['kpr-u1'].surfaces['b2']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b3l'], 
    mdb.models['Model-1'].rootAssembly.instances['b0'].surfaces['b1']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b3l'], 
    mdb.models['Model-1'].rootAssembly.instances['kpl-u0'].surfaces['b2']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b3r'], 
    mdb.models['Model-1'].rootAssembly.instances['b1'].surfaces['b1']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b3r'], 
    mdb.models['Model-1'].rootAssembly.instances['kpl-u1'].surfaces['b2']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b4l'], 
    mdb.models['Model-1'].rootAssembly.instances['col'].surfaces['c00']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b4l'], 
    mdb.models['Model-1'].rootAssembly.instances['cwr1'].surfaces['b0']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b4l'], 
    mdb.models['Model-1'].rootAssembly.instances['cwr3'].surfaces['b0']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b4l'], 
    mdb.models['Model-1'].rootAssembly.instances['kpl-u1'].surfaces['b1']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b4l'], 
    mdb.models['Model-1'].rootAssembly.instances['kpr-u1'].surfaces['b1']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b4r'], 
    mdb.models['Model-1'].rootAssembly.instances['col'].surfaces['c02']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b4r'], 
    mdb.models['Model-1'].rootAssembly.instances['cwr4'].surfaces['b0']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b4r'], 
    mdb.models['Model-1'].rootAssembly.instances['cwr6'].surfaces['b0']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b4r'], 
    mdb.models['Model-1'].rootAssembly.instances['kpl-u0'].surfaces['b1']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b4r'], 
    mdb.models['Model-1'].rootAssembly.instances['kpr-u0'].surfaces['b1']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b5l'], 
    mdb.models['Model-1'].rootAssembly.instances['col'].surfaces['c03']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b5l'], 
    mdb.models['Model-1'].rootAssembly.instances['cwr0'].surfaces['b0']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b5l'], 
    mdb.models['Model-1'].rootAssembly.instances['cwr2'].surfaces['b0']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b5l'], 
    mdb.models['Model-1'].rootAssembly.instances['kpl-d1'].surfaces['b1']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b5l'], 
    mdb.models['Model-1'].rootAssembly.instances['kpr-d1'].surfaces['b1']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b5r'], 
    mdb.models['Model-1'].rootAssembly.instances['col'].surfaces['c01']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b5r'], 
    mdb.models['Model-1'].rootAssembly.instances['cwr5'].surfaces['b0']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b5r'], 
    mdb.models['Model-1'].rootAssembly.instances['cwr7'].surfaces['b0']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b5r'], 
    mdb.models['Model-1'].rootAssembly.instances['kpl-d0'].surfaces['b1']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b5r'], 
    mdb.models['Model-1'].rootAssembly.instances['kpr-d0'].surfaces['b1']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b6l'], 
    mdb.models['Model-1'].rootAssembly.instances['b0'].surfaces['b5']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b6l'], 
    mdb.models['Model-1'].rootAssembly.instances['sp0'].surfaces['b1']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b6r'], 
    mdb.models['Model-1'].rootAssembly.instances['b1'].surfaces['b5']), (
    mdb.models['Model-1'].rootAssembly.surfaces['b6r'], 
    mdb.models['Model-1'].rootAssembly.instances['sp1'].surfaces['b1'])), 
    stepName='Initial', useAllstar=OFF)
mdb.models['Model-1'].interactions['Int-1'].contactPropertyAssignments.appendInStep(
    assignments=((GLOBAL, SELF, 'IntProp-1'), ), stepName='Initial')
# Save by 14analysis on 2018_10_30-14.41.58; build 6.12-1 2012_03_13-20.23.18 119612


#------------------------------------------------------------------------------------
#剛体の設定

mdb.models['Model-1'].rootAssembly.regenerate()
mdb.models['Model-1'].rootAssembly.Set(name='RP1', referencePoints=(
    mdb.models['Model-1'].rootAssembly.referencePoints[292], ))
mdb.models['Model-1'].rootAssembly.Set(name='RP2', referencePoints=(
    mdb.models['Model-1'].rootAssembly.referencePoints[293], ))
mdb.models['Model-1'].rootAssembly.Set(name='RP3', referencePoints=(
    mdb.models['Model-1'].rootAssembly.referencePoints[294], ))
mdb.models['Model-1'].rootAssembly.Set(name='RP4', referencePoints=(
    mdb.models['Model-1'].rootAssembly.referencePoints[295], ))
# Save by 14analysis on 2018_10_30-14.06.05; build 6.12-1 2012_03_13-20.23.18 119612

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
