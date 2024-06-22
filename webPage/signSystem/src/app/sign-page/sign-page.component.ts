import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';

@Component({
  selector: 'app-sign-page',
  templateUrl: './sign-page.component.html',
  styleUrl: './sign-page.component.css'
})
export class SignPageComponent implements OnInit{
  form! : FormGroup;
  showRe : boolean = false

  constructor(private http : HttpClient){}

  ngOnInit(): void {
    this.form = new FormGroup({
      username : new FormControl("", [Validators.required]),
      email : new FormControl("", [Validators.required]),
      password : new FormControl("", [Validators.required, this.checkLowerCase(), this.checkUpperCase(), this.checkNumeric(), this.checkSpecialCharacter(), Validators.minLength(6)]),
      rePassword : new FormControl("", [Validators.required])
    })
  }

  checkRePassword() : ValidatorFn {
    return (text: AbstractControl) : ValidationErrors | null => {
      const password = text.value;

      if(!password){
        return null
      }

      let result: boolean = password === this.form.get("password")?.value

      return !result ? {lowerCase : true} : null
    }
  }

  checkLowerCase() : ValidatorFn {
    return (text: AbstractControl) : ValidationErrors | null => {
      const password = text.value;

      if(!password){
        return null
      }

      let result: boolean = /[a-z]+/.test(password);

      return !result ? {lowerCase : true} : null
    }
  }

  checkUpperCase() : ValidatorFn{
    return (text: AbstractControl) : ValidationErrors | null => {
      const password = text.value;

      if(!password){
        return null
      }

      let result: boolean = /[A-Z]+/.test(password);

      return !result ? {upperCase : true} : null
    }
  }

  checkNumeric() : ValidatorFn{
    return (text: AbstractControl) : ValidationErrors | null => {
      const password = text.value;

      if(!password){
        return null
      }

      let result: boolean = /[0-9]+/.test(password);

      return !result ? {numeric : true} : null
    }
  }

  checkSpecialCharacter() : ValidatorFn{
    return (text: AbstractControl) : ValidationErrors | null => {
      const specialCharacters : string [] = ["@", "#", "$", "%", "^", "&", "*", "`", "(", ")", "-", "_", "+", "=", "{", "}", "|", '\"', "/", "<", ">", ",", ".", ";", ":", "'", "?", "!"]
      const password : string = text.value;

      if(!password){
        return null
      }

      let result: boolean = false;

     for (let i : number = 0; i<password.length; i++){
        if(specialCharacters.includes(password[i])){
          
          result = true;
          break;
        }
     }

      return !result ? {specialCharacter : true} : null
    }
  }



  showRequirements(){
    this.showRe = true
  }

  sign(){
    const details : FormData = new FormData();
    details.append("username", this.form.value.username);
    details.append("password", this.form.value.password);
    details.append("email", this.form.value.email);

    this.http.post("http://localhost/library/sign.php", details);
    this.http.post("http://localhost/library/email.php", details).subscribe(res => console.log(res));
  }

  
}
