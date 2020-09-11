import {useForm} from "react-hook-form";
import {Button, Form, FormGroup, TextInput, Tile} from "carbon-components-react";
import React from "react";

export default function LoginForm(props: { onSubmit: any }) {
  const { register, handleSubmit, errors } = useForm();
  // console.log(watch("username")); // watch input value by passing the name of it

  return (
    <Tile className={'login-form'}>
      <h4><strong>Uservist</strong> Log in</h4>
      <p>Dont have an account? Ask your admin for an invite.</p>
      <hr />
      <Form onSubmit={handleSubmit(props.onSubmit)}>
        <FormGroup legendText={''}>
          <TextInput
            id="username"
            name="username"
            labelText="Username"
            placeholder="Enter your username"
            invalidText="Invalid username."
            invalid={errors.username}
            ref={register({ required: true })}
          />
        </FormGroup>
        <FormGroup legendText={''}>
          <TextInput
            id="password"
            name="password"
            type="password"
            labelText="Password"
            placeholder="Enter your password"
            invalidText="Invalid password."
            invalid={errors.password}
            ref={register({ required: true })}
          />
        </FormGroup>
        <Button kind="primary" tabIndex={0} type="submit">
          Log in
        </Button>
      </Form>
    </Tile>
  )
}