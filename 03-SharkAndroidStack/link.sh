#path to SharkFW
SOURCE=/Users/mario/Work/SharkFW/src/java

#Fixed to java sources, do not change
ROOT=./app/src/main/java

#Cleanup previous links and files - be careful
rm -rf $ROOT/net/sharkfw

#android
mkdir -p $ROOT/net/sharkfw/peer
ln -s $SOURCE/android/net/sharkfw/peer/*.java                                   $ROOT/net/sharkfw/peer/

mkdir -p $ROOT/net/sharkfw/protocols/{wifidirect,nfc}
ln -s $SOURCE/android/net/sharkfw/protocols/wifidirect/*.java                   $ROOT/net/sharkfw/protocols/wifidirect/
#ln -s $SOURCE/android/net/sharkfw/protocols/nfc/*.java                         $ROOT/net/sharkfw/protocols/nfc/

#core
mkdir -p $ROOT/net/sharkfw/kep/format
ln -s $SOURCE/core/net/sharkfw/kep/*.java                                       $ROOT/net/sharkfw/kep/
ln -s $SOURCE/core/net/sharkfw/kep/format/*.java                                $ROOT/net/sharkfw/kep/format

mkdir -p $ROOT/net/sharkfw/knowledgeBase/geom/inmemory
mkdir -p $ROOT/net/sharkfw/knowledgeBase/{inmemory,sync}
ln -s $SOURCE/core/net/sharkfw/knowledgeBase/*.java                             $ROOT/net/sharkfw/knowledgeBase/
ln -s $SOURCE/core/net/sharkfw/knowledgeBase/geom/*.java                        $ROOT/net/sharkfw/knowledgeBase/geom/
ln -s $SOURCE/core/net/sharkfw/knowledgeBase/geom/inmemory/*.java               $ROOT/net/sharkfw/knowledgeBase/geom/inmemory/
ln -s $SOURCE/core/net/sharkfw/knowledgeBase/inmemory/*.java                    $ROOT/net/sharkfw/knowledgeBase/inmemory/
ln -s $SOURCE/core/net/sharkfw/knowledgeBase/sync/*.java                        $ROOT/net/sharkfw/knowledgeBase/sync/

mkdir -p $ROOT/net/sharkfw/kp
ln -s $SOURCE/core/net/sharkfw/kp/*.java                                        $ROOT/net/sharkfw/kp/

mkdir -p $ROOT/net/sharkfw/peer
ln -s $SOURCE/core/net/sharkfw/peer/*.java                                      $ROOT/net/sharkfw/peer/

mkdir -p $ROOT/net/sharkfw/pki
ln -s $SOURCE/core/net/sharkfw/pki/*.java                                       $ROOT/net/sharkfw/pki/

mkdir -p $ROOT/net/sharkfw/protocols/m2s
ln -s $SOURCE/core/net/sharkfw/protocols/*.java                                 $ROOT/net/sharkfw/protocols/
ln -s $SOURCE/core/net/sharkfw/protocols/m2s/*.java                             $ROOT/net/sharkfw/protocols/m2s/

mkdir -p $ROOT/net/sharkfw/security/{key,utility}
mkdir -p $ROOT/net/sharkfw/security/pki/storage/filesystem
ln -s $SOURCE/core/net/sharkfw/security/key/*.java                              $ROOT/net/sharkfw/security/key/
ln -s $SOURCE/core/net/sharkfw/security/pki/*.java                              $ROOT/net/sharkfw/security/pki/
ln -s $SOURCE/core/net/sharkfw/security/pki/storage/*.java                      $ROOT/net/sharkfw/security/pki/storage/
ln -s $SOURCE/core/net/sharkfw/security/pki/storage/filesystem/*.java           $ROOT/net/sharkfw/security/pki/storage/filesystem/
ln -s $SOURCE/core/net/sharkfw/security/utility/*.java                          $ROOT/net/sharkfw/security/utility/

mkdir -p $ROOT/net/sharkfw/system
ln -s $SOURCE/core/net/sharkfw/system/*.java                                    $ROOT/net/sharkfw/system/

# #j2seMail
mkdir -p $ROOT/net/sharkfw/protocols/mail
ln -s $SOURCE/j2seMail/net/sharkfw/protocols/mail/*.java                      $ROOT/net/sharkfw/protocols/mail


#j2se_android
mkdir -p $ROOT/net/sharkfw/knowledgeBase/filesystem
ln -s $SOURCE/j2se_android/net/sharkfw/knowledgeBase/filesystem/*.java          $ROOT/net/sharkfw/knowledgeBase/filesystem/

mkdir -p $ROOT/net/sharkfw/peer
ln -s $SOURCE/j2se_android/net/sharkfw/peer/*.java                              $ROOT/net/sharkfw/peer/

mkdir -p $ROOT/net/sharkfw/protocols/{http,tcp}
ln -s $SOURCE/j2se_android/net/sharkfw/protocols/http/*.java                    $ROOT/net/sharkfw/protocols/http/
ln -s $SOURCE/j2se_android/net/sharkfw/protocols/tcp/*.java                     $ROOT/net/sharkfw/protocols/tcp/
