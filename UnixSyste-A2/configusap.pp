#Unix System Application Program Assignment 2
#class usap_a::config for edit files and setup servers
class usap_a2::config {
  notify { "usap_a2 Assignment Set-up Servers":
  }

#4.a root cannot login from SSH
  file {'/etc/ssh/sshd_config':
    notify  => Service['sshd'],
    owner  => 'root',
    group  => 'root',
    mode   => '0644',
    source => '/etc/puppetlabs/code/environments/production/modules/usap/sshd_confg',
  }
  
#4.b Set Apache’s document root to /var/www
  file { '/etc/httpd/conf/httpd.conf' :
    notify  => Service['httpd'],
    owner   => 'root',
    group   => 'root',
    mode    => '0644',
    require => Package['httpd'],
    source  => '/etc/puppetlabs/code/environments/production/modules/usap/httpd.conf',
  }
  
#4.c becca can use sudo to a root shell
  file { '/etc/sudoers':
    owner   => 'root',
    group   => 'root',
    mode    => '0440',
    require => Package['sudo'],
    source  => '/etc/puppetlabs/code/environments/production/modules/usap/sudoers',
  }

#4.d set strace in every's shell
  file { "/etc/profile.d/strace.sh":
    content => 'alias strace="/usr/bin/strace"',
  }

#7. Mysql subscribes to the file
  file { '/etc/my.cnf':
    owner   => 'root',
    group   => 'root',
    mode    => '0440',
    require => Package['mysql-server'],
    source  => '/etc/puppetlabs/code/environments/production/modules/usap/my.cnf',
  }


#5.Write the hosts file, and add a new record usap.rmit.edu.au 131.170.1.1.
  file { '/etc/hosts' :
    owner  => 'root',
    group  => 'root',
    mode   => '0644',
    source => '/etc/puppetlabs/code/environments/production/modules/usap/hosts',
  }

#7.subscribe servers to the file
service { 'sshd':
    ensure  => 'running',
    enable  => true,
    require => Package['openssh'],
  }

service { 'httpd':
    ensure  => 'running',
    enable  => true,
    require => Package['httpd'],
  }

service { 'mysqld':
    ensure  => 'running',
    enable  => true,
    require => Package['mysql-server'],
  }

#8. Use ERB template to set-up index.html webpage.
  $student_number = 'S3534987'
  file { '/var/www/index.html':
    content => template('/etc/puppetlabs/code/environments/production/modules/usap/index.html.erb')
  }
}
