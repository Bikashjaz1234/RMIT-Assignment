#Unix System Application Program Assignment 2
#class usap_a2 for this assignment


class usap_a2 {
  notify { 'usap_a2 Assignment':
  }

#1.a,b,c,d,e Create user name is becca, set her group, home dir, password, uid and shell.
  user { 'becca':
    ensure   => present,
    home     => '/home/becca',
    groups   => ['sysadmin', 'car'],
    password => 'rmit',
    uid      => 10014987,
    shell    => '/bin/bash',
  }

#1.b Create the group
  group { 'car':
    ensure => present,
  }

#1.b Create the group
  group { 'sysadmin':
    ensure => present,
    system => true
  }

#3.a,b,c,d,e Install these servers
  package { ['openssl', 'httpd', 'mysql','strace', 'sudo']:
    ensure => installed
  }


}