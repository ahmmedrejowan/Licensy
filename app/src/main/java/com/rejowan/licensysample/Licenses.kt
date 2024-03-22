package com.rejowan.licensysample

enum class Licenses(shortName: String, fullName: String, description: String, url: String) {


    APACHE_2_0(
        "Apache 2.0",
        "Apache License 2.0",
        apache_short_description,
        "https://www.apache.org/licenses/LICENSE-2.0"
    ),
    GPL_3_0(
        "GPL 3.0",
        "GNU General Public License 3.0",
        gpl_short_description,
        "https://www.gnu.org/licenses/gpl-3.0.html"
    ),
    MIT(
        "MIT",
        "MIT License",
        mit_short_description,
        "https://opensource.org/licenses/MIT"
    ),
    BSD_2_CLAUSE(
        "BSD 2-Clause",
        "BSD 2-Clause \"Simplified\" License",
        bsd_2_clause_short_description,
        "https://opensource.org/licenses/BSD-2-Clause"
    ),
    BSD_3_CLAUSE(
        "BSD 3-Clause",
        "BSD 3-Clause \"New\" or \"Revised\" License",
        bsd_3_clause_short_description,
        "https://opensource.org/licenses/BSD-3-Clause"
    ),
    BSL_1(
        "BSL 1.0",
        "Boost Software License 1.0",
        boost_short_description,
        "https://www.boost.org/LICENSE_1_0.txt"
    ),
    CC_0(
        "CC0 1.0 Universal",
        "Creative Commons Zero v1.0 Universal",
        creative_commons_zero_short_description,
        "https://creativecommons.org/publicdomain/zero/1.0/legalcode.en"
    ),
    EPL_2_0(
        "EPL 2.0",
        "Eclipse Public License 2.0",
        eclipse_public_license_2_0_short_description,
        "https://www.eclipse.org/legal/epl-2.0"
    ),
    AGPL_3_0(
        "AGPL 3.0",
        "GNU Affero General Public License 3.0",
        gnu_agpl3_short_description,
        "https://www.gnu.org/licenses/agpl-3.0.html"
    ),
    GPL_2_0(
        "GPL 2.0",
        "GNU General Public License 2.0",
        gpl_2_0_short_description,
        "https://www.gnu.org/licenses/old-licenses/gpl-2.0.html"
    ),
    LGPL_LESS_2_1(
        "LGPL 2.1",
        "GNU Lesser General Public License 2.1",
        gpl_less_2_1_short_description,
        "https://www.gnu.org/licenses/old-licenses/lgpl-2.1.html"
    ),
    MPL_2_0(
        "MPL 2.0",
        "Mozilla Public License 2.0",
        mozilla_public_license_2_0_short_description,
        "https://www.mozilla.org/en-US/MPL/2.0/"
    ),
    UNLICENSE(
        "Unlicense",
        "The Unlicense",
        unlicense_short_description,
        "https://unlicense.org/"
    );

    val shortName = shortName
    val fullName = fullName
    val description = description
    val url = url


}

private const val apache_short_description =
    "Licensed under the Apache License, Version 2.0 (the \"License\");\n" + "you may not use this file except in compliance with the License.\n" + "You may obtain a copy of the License at\n" + "\n" + "   https://www.apache.org/licenses/LICENSE-2.0\n" + "\n" + "Unless required by applicable law or agreed to in writing, software\n" + "distributed under the License is distributed on an \"AS IS\" BASIS,\n" + "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" + "See the License for the specific language governing permissions and\n" + "limitations under the License."

private const val gpl_short_description =
    "This program is free software: you can redistribute it and/or modify\n" + "it under the terms of the GNU General Public License as published by\n" + "the Free Software Foundation, either version 3 of the License, or\n" + "(at your option) any later version.\n" + "\n" + "This program is distributed in the hope that it will be useful,\n" + "but WITHOUT ANY WARRANTY; without even the implied warranty of\n" + "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n" + "GNU General Public License for more details.\n" + "\n" + "You should have received a copy of the GNU General Public License\n" + "along with this program.  If not, see <https://www.gnu.org/licenses/>."

private const val mit_short_description =
    "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the \"Software\"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:\n" + "\n" + "The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.\n" + "\n" + "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE."

private const val bsd_2_clause_short_description =
    "Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:\n" + "\n" + "1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.\n" + "2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.\n" + "\n" + "THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS IS\" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE."

private const val bsd_3_clause_short_description =
"Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:\n" + "\n" + "1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.\n" + "2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.\n" + "3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.\n" + "\n" + "THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS IS\" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE."

private const val boost_short_description =
    "Boost Software License - Version 1.0 - August 17th, 2003\n" + "\n" + "Permission is hereby granted, free of charge, to any person or organization obtaining a copy of the software and accompanying documentation covered by this license (the \"Software\") to use, reproduce, display, distribute, execute, and transmit the Software, and to prepare derivative works of the Software, and to permit third-parties to whom the Software is furnished to do so, all subject to the following:\n" + "\n" + "The copyright notices in the Software and this entire statement, including the above license grant, this restriction and the following disclaimer, must be included in all copies of the Software, in whole or in part, and all derivative works of the Software, unless such copies or derivative works are solely in the form of machine-executable object code generated by a source language processor.\n" + "\n" + "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, TITLE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR ANYONE DISTRIBUTING THE SOFTWARE BE LIABLE FOR ANY DAMAGES OR OTHER LIABILITY, WHETHER IN CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE."

private const val creative_commons_zero_short_description =
    "(Summary) Releases software into the public domain, or otherwise grants permission to use it for any purpose. Disclaims patent licenses."

private const val eclipse_public_license_2_0_short_description =
    "(Summary) This license, made and used by the Eclipse Foundation, is similar to GPL but allows you to link code under the license to proprietary applications. You may also license binaries under a proprietary license, as long as the source code is available under EPL."

private const val gnu_agpl3_short_description =
    "This program is free software: you can redistribute it and/or modify " + "it under the terms of the GNU Affero General Public License as published by " + "the Free Software Foundation, either version 3 of the License, or " + "(at your option) any later version.\n" + "\n" + "This program is distributed in the hope that it will be useful, " + "but WITHOUT ANY WARRANTY; without even the implied warranty of " + "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the " + "GNU Affero General Public License for more details.\n" + "\n" + "You should have received a copy of the GNU Affero General Public License " + "along with this program.  If not, see <https://www.gnu.org/licenses/>."

private const val gpl_2_0_short_description =
    "This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.\n" + "\n" + "This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.\n" + "\n" + "You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA"

private const val gpl_less_2_1_short_description =
    "This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free Software Foundation; either version 2.1 of the License, or (at your option) any later version.\n" + "\n" + "This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.\n" + "\n" + "You should have received a copy of the GNU Lesser General Public License along with this library; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA"

private const val mozilla_public_license_2_0_short_description =
    "This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/."

private const val unlicense_short_description =
    "This is free and unencumbered software released into the public domain.\n" + "\n" + "Anyone is free to copy, modify, publish, use, compile, sell, or\n" + "distribute this software, either in source code form or as a compiled\n" + "binary, for any purpose, commercial or non-commercial, and by any\n" + "means.\n" + "\n" + "In jurisdictions that recognize copyright laws, the author or authors\n" + "of this software dedicate any and all copyright interest in the\n" + "software to the public domain. We make this dedication for the benefit\n" + "of the public at large and to the detriment of our heirs and\n" + "successors. We intend this dedication to be an overt act of\n" + "relinquishment in perpetuity of all present and future rights to this\n" + "software under copyright law.\n" + "\n" + "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND,\n" + "EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF\n" + "MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN\n" + "NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n" + "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING\n" + "FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER\n" + "DEALINGS IN THE SOFTWARE."


